package com.rath.logger.impl;

import java.util.Date;
import java.util.List;

import com.rath.logger.LogAppender;
import com.rath.logger.LogLevel;
import com.rath.logger.Logger;

public class LoggerImpl implements Logger {
	
	// 1 for trace and 6 for fatal
	private int logLevel;
	
	private List<LogAppender> appenders;
	
	private Class<?> cls;
	
	public LoggerImpl (List<LogAppender> appenders, int logLevel, Class<?> cls) {
		this.appenders = appenders;
		this.logLevel = logLevel;
		this.cls = cls;
	}
	
	private void enqueue (Date time, Thread thread, LogLevel level, Class<?> cls, String message, Throwable th) {
		for (LogAppender each : appenders) {
			each.addToQueue(time, thread, level, cls, message, th);
		}
	}
	
	private String mergeMessage (String... strings) {
		String message = strings[0];
		for (int i = 1 ; i < strings.length ; i++) {
			message.replaceFirst("{}", strings[i]);
		}
		return message;
	}

	@Override
	public void trace(String... strings) {
		if (logLevel <= 1) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.TRACE, cls, message, null);
		}
	}

	@Override
	public void debug(String... strings) {
		if (logLevel <= 2) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.DEBUG, cls, message, null);
		}
	}

	@Override
	public void info(String... strings) {
		if (logLevel <= 3) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.INFO, cls, message, null);
		}
	}

	@Override
	public void warn(String... strings) {
		warn(null, strings);
	}

	@Override
	public void error(String... strings) {
		error(null, strings);
	}

	@Override
	public void fatal(String... strings) {
		fatal(null, strings);
	}

	@Override
	public void warn(Throwable th, String... strings) {
		if (logLevel <= 4) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.WARN, cls, message, th);
		}
	}

	@Override
	public void error(Throwable th, String... strings) {
		if (logLevel <= 5) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.ERROR, cls, message, th);
		}
	}

	@Override
	public void fatal(Throwable th, String... strings) {
		if (logLevel <= 6) {
			String message = null;
			if (strings.length > 0) {
				message = mergeMessage(strings);
			}
			enqueue(new Date(), Thread.currentThread(), LogLevel.FATAL, cls, message, th);
		}
	}

}
