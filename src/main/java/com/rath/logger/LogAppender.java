package com.rath.logger;

import java.util.Date;

public interface LogAppender extends Runnable {
	
	public void append (Data data);
	
	public void addToQueue(Date time, Thread thread, LogLevel level, Class<?> cls, String message, Throwable th);
	
	public void stop();
}
