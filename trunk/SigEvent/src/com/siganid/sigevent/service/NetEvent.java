package com.siganid.sigevent.service;

import com.siganid.sigevent.annotation.ThreadType;

public class NetEvent extends Event {
	public NetEvent() {
		super();
	}

	public NetEvent(String type) {
		super(type);
	}

	public NetEvent(ThreadType threadType) {
		this.threadType = threadType;
	}

	public NetEvent(String type, ThreadType threadType) {
		super(type, threadType);
		this.type = type;
		this.threadType = threadType;
	}
	
}
