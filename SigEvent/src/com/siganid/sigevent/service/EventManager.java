package com.siganid.sigevent.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.text.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.siganid.sigevent.annotation.EventType;
import com.siganid.sigevent.annotation.ThreadType;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

public class EventManager {

	private EventManager() {
	}

	static EventManager responseManager = null;

	public static EventManager init() {

		if (responseManager == null) {
			responseManager = new EventManager();
		}
		return responseManager;
	}

	HashMap<String, HashMap<String, EventListener>> responmap = new HashMap<String, HashMap<String, EventListener>>();
	HashMap<String, String> methodmap = new HashMap<String, String>();
	HashMap<String, Class> parmap = new HashMap<String, Class>();

	/**
	 * 注册
	 * 
	 * @param responseListener
	 */
	public void regedit(EventListener responseListener) {
		Method[] methods = responseListener.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Class<?>[] par = methods[i].getParameterTypes();
			if (par.length == 1 && Event.class.isAssignableFrom(par[0])) {
				if (methods[i].isAnnotationPresent(EventType.class)) {
					EventType eventType = methods[i]
							.getAnnotation(EventType.class);
					methodmap.put(eventType.value()
							+ responseListener.getClass().getName(),
							methods[i].getName());
					parmap.put(eventType.value()
							+ responseListener.getClass().getName(), par[0]);
					regeditResponse(responseListener, eventType.value());
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * @param types
	 * @param responseListener
	 */
	private void regeditResponse(EventListener responseListener,
			String... types) {
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			HashMap<String, EventListener> listenermap = responmap.get(type);
			if (listenermap == null) {
				// 当前类型的队列没有 没有创建一个队列。
				listenermap = new HashMap<String, EventListener>();
				responmap.put(type, listenermap);
			}
			String classname = responseListener.getClass().getName();
			// 无论以前有没有 都将以前的替换掉。
			listenermap.put(classname, responseListener);
		}
	}

	/**
	 * 移除所有的监听
	 * 
	 * @param classname
	 */
	public void unRegeditResponse(String classname) {
		for (Iterator<?> iterator = responmap.entrySet().iterator(); iterator
				.hasNext();) {
			HashMap<?, ?> listenmap = (HashMap<?, ?>) ((Map.Entry) iterator
					.next()).getValue();
			listenmap.remove(classname);
		}
	}

	/**
	 * 移除所有的监听
	 * 
	 * @param classname
	 */
	public void unRegeditResponse(Object obj) {
		unRegeditResponse(obj.getClass().getName());
	}

	/**
	 * 发送事件
	 * 
	 * @param event
	 */
	public void sentEvent(final Event event) {
		log("发送：" + event.getType());

		tranLateByThread(event, null);

	}

	/**
	 * 发送事件
	 * 
	 * @param event
	 */
	public void sentEvent(final Event event,
			final EventListener... eventListener) {
		log("发送：" + event.getType());
		tranLateByThread(event, eventListener);
	}

	private void tranLateByThread(final Event event,
			final EventListener... eventListener) {
		if (event.getThreadType() == ThreadType.UI) {
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {

				@Override
				public void run() {
					excuseResponListener(event, eventListener);
				}
			});
		} else if (event.getThreadType() == ThreadType.NewThread) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					excuseResponListener(event, eventListener);
				}
			}).start();
		} else {
			excuseResponListener(event, eventListener);
		}

	}

	/**
	 * 执行事件
	 * 
	 * @param event
	 */
	public void excuseResponListener(Event event,
			EventListener... targeEventListener) {

		// 根据时间获取已经注册的列表
		HashMap<String, EventListener> listenermap = responmap.get(event
				.getType());
		if (listenermap == null) {
			return;
		}

		for (Iterator<?> iterator = listenermap.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry listener = (Map.Entry) iterator.next();
			EventListener eventListener = ((EventListener) listener.getValue());
			if (targeEventListener != null && targeEventListener.length > 0) {
				boolean hasEventReceiver = false;
				for (int i = 0; i < targeEventListener.length; i++) {
					if (eventListener == targeEventListener[i]) {
						hasEventReceiver = true;
					}
				}
				if (!hasEventReceiver) {
					continue;
				}
			}
			log("执行对象: " + listener.getValue().getClass().getName() + "执行类型："
					+ event.getType());
			String methodname = methodmap.get(event.getType()
					+ eventListener.getClass().getName());
			Class perClass = parmap.get(event.getType()
					+ eventListener.getClass().getName());
			try {
				Method method = eventListener.getClass().getMethod(methodname,
						perClass);
				method.invoke(eventListener, event);
			} catch (Exception e) {
				log("事件类型对象不上");
				e.printStackTrace();
			}
			log("-----------------------------------------------");

		}
	}

	public static void log(String string) {
		Log.d("event", string);
	}

	public void getTraces(Event event) {
		StackTraceElement[] trace = Thread.getAllStackTraces().get(
				Thread.currentThread());
		for (int i = 0; i < trace.length; i++) {
			event.addExcuseClassname(trace[i].getClassName());
		}
	}

	public void excuseInUi(Runnable runnable) {
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(runnable);
	}
}
