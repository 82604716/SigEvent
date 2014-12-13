package com.siganid.sigevent.service;

import java.util.ArrayList;

import com.siganid.sigevent.annotation.ThreadType;

public class Event {

	public String getType() {
		return type;
	}

	public ThreadType getThreadType() {
		return threadType;
	}

	ArrayList<String> tracelist = new ArrayList<String>();

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 更新类型
	 */
	public String type = "";
	public ThreadType threadType = ThreadType.UI;

	public Event() {
		this.type = "";
	}

	public Event(String type) {
		this.type = type;
	}

	public Event(ThreadType threadType) {
		this.threadType = threadType;
	}

	public Event(String type, ThreadType threadType) {
		this.type = type;
		this.threadType = threadType;
	}

	public void addExcuseClassname(String classname) {
		tracelist.add(classname);
	}

	public void setExcuseClassname(ArrayList<String> tracelist) {
		this.tracelist = tracelist;
	}

	public ArrayList<String> getExcuseClassname() {
		return tracelist;
	}
}
