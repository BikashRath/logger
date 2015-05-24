package com.rath.logger.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.rath.logger.Configuration;
import com.rath.logger.LogAppender;
import com.rath.logger.Logger;

public class LogFactory {
	
	private static List<LogAppender> appenders;

	public static Logger getLogger (Class<?> cls) {
		if (null == appenders) {
			setAppenders();
		}
		return new LoggerImpl(appenders, getLevel(), cls);
	}
	
	public static void stopLoggerThreads() {
		if (null != appenders) {
			for (LogAppender each : appenders) {
				each.stop();
			}
		}
	}
	
	private static int getLevel () {
		String level = Configuration.getInstance().get("level", "debug");
		
		if (level.equalsIgnoreCase("trace")) {
			return 1;
		} else if (level.equalsIgnoreCase("debug")) {
			return 2;
		} else if (level.equalsIgnoreCase("info")) {
			return 3;
		} else if (level.equalsIgnoreCase("warn")) {
			return 4;
		} else if (level.equalsIgnoreCase("error")) {
			return 5;
		} else if (level.equalsIgnoreCase("fatal")) {
			return 6;
		} else {
			return 2;
		}
	}
	
	private static void setAppenders() {
		appenders = new ArrayList<LogAppender>();
		
		Reflections reflections = new Reflections();
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(com.rath.logger.annotations.Appender.class);
		for (Class<?> each : classes) {
			Object obj;
			try {
				obj = each.newInstance();
				if (obj instanceof LogAppender) {
					LogAppender appender = (LogAppender)obj;
					new Thread(appender).start();
					appenders.add(appender);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
