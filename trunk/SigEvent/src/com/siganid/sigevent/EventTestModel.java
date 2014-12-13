package com.siganid.sigevent;

import android.content.Context;

import com.siganid.sigevent.annotation.EventType;
import com.siganid.sigevent.service.EventListener;
import com.siganid.sigevent.service.EventManager;
import com.siganid.sigevent.service.NetEvent;

public class EventTestModel implements EventListener {
	public EventTestModel() {
		EventManager.init().regedit(this);
	}

	@EventType
	public void test2(NetEvent event) {
		System.out.println("执行22sss222");
		EventManager.init().excuseInUi(new Runnable() {
			@Override
			public void run() {
				System.out.println("test");
			}
		});
	}
}
