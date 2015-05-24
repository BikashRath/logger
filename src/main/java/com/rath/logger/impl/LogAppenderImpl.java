package com.rath.logger.impl;

import com.rath.logger.AbstractLogAppender;
import com.rath.logger.Data;
import com.rath.logger.annotations.Appender;

@Appender
public class LogAppenderImpl extends AbstractLogAppender {

	@Override
	public void append(Data data) {
		StringBuilder sb = new StringBuilder();
		if (null != data.time) {
			sb.append(data.time.toString());
			sb.append("\t");
		}
		if (null != data.thread) {
			sb.append(data.thread.getName());
			sb.append("\t");
		}
		sb.append(data.level);
		sb.append("\t");
		sb.append(data.cls.getName());
		sb.append("\t");
		if (null != data.message) {
			sb.append(data.message);
			sb.append("\t");
		}
		if (null != data.th) {
			sb.append(data.th.toString());
		}
		System.out.println(sb.toString());
	}

}
