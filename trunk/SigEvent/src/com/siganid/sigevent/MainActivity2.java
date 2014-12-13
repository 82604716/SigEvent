package com.siganid.sigevent;

import com.example.sigevent.R;
import com.siganid.sigevent.annotation.EventType;
import com.siganid.sigevent.service.Event;
import com.siganid.sigevent.service.EventListener;
import com.siganid.sigevent.service.EventManager;
import com.siganid.sigevent.service.NetEvent;
import com.siganid.sigevent.service.UIThreadService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity implements EventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// EventManager.getInstance(this).regedit(this);
		// EventTestModel eventTestModel = new EventTestModel(this);
		// EventManager.getInstance(this).sentEvent(new NetEvent());

		// EventManager.getInstance(this).sentEvent(new NetEvent("1345"));
	}


	@EventType
	public void test(NetEvent event) {
		System.out.println("NetEvent执行");
	}

	@EventType("1345")
	public void test2(NetEvent event) {
		System.out.println("执行22222");
	}

	public void toTWO(View v) {
		Intent in = new Intent();
		in.setClass(this, MainActivity2.class);
		startActivity(in);
	}
}
