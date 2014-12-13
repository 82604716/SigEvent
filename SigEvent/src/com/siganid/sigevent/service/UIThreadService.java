package com.siganid.sigevent.service;

import android.app.Activity;
import android.os.Handler;

public class UIThreadService {

	Handler uiHandler;

	public void doInUIThread(Activity activity) {
		uiHandler = activity.getWindow().getDecorView().getHandler();
		System.out.println("handler:" + uiHandler);
	}
}
