package com.rath.logger;

import java.util.Date;

public class Data {

	public Date time;
	public Thread thread;
	public LogLevel level;
	public Class<?> cls;
	public String message;
	public Throwable th;

	public Data(Date time, Thread thread, LogLevel level, Class<?> cls, String message, Throwable th) {
		this.time = time;
		this.thread = thread;
		this.level = level;
		this.cls = cls;
		this.message = message;
		this.th = th;
	}

}
