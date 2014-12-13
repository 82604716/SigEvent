package com.siganid.sigevent;

import com.example.sigevent.R;
import com.siganid.sigevent.annotation.EventType;
import com.siganid.sigevent.annotation.ThreadType;
import com.siganid.sigevent.service.Event;
import com.siganid.sigevent.service.EventListener;
import com.siganid.sigevent.service.EventManager;
import com.siganid.sigevent.service.NetEvent;
import com.siganid.sigevent.service.UIThreadService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;

public class MainActivity extends Activity implements EventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		EventManager.init().sentEvent(new Event());
		EventManager.init().sentEvent(new NetEvent("callback"));
		
	}

	@EventType
	public void test(Event event) {
		System.out.println(Thread.currentThread().getId());
		System.out.println("NetEvent执行");
	}

	@EventType("callback")
	public void test2(NetEvent event) {
		System.out.println("执行22222");
	}

}
