package com.rath.logger;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractLogAppender implements LogAppender {
	
	protected ArrayBlockingQueue<Data> queue;
	private boolean run = true;
	protected int maxQueueSize = -1;
	
	@Override
	public void run() {
		initialize();
		while (run) {
			Data data;
			try {
				data = queue.take();
				append(data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addToQueue(Date time, Thread thread, LogLevel level, Class<?> cls, String message, Throwable th) {
		try {
			queue.put(new Data(time, thread, level, cls, message, th));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		if (null == queue) {
			int queueSize =  1000;
			if (maxQueueSize > 0) {
				queueSize = maxQueueSize;
			} else {
				try {
					queueSize = Integer.parseInt(Configuration.getInstance().get("queuesize", "1000"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			queue = new ArrayBlockingQueue<Data>(queueSize);
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		run = false;
	}
	
	@Override
	public void stop() {
		run = false;
	}
}