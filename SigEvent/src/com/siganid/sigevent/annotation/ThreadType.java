package com.siganid.sigevent.annotation;

public enum ThreadType {

	/**
	 * 回调在ui线程执行
	 */
	UI,
	/**
	 * 回调跟发送者线程一样
	 */
	LikeSent,

	/**
	 * 回调在新线程运行
	 */
	NewThread;
}
