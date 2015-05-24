package com.rath;

import com.rath.logger.Logger;
import com.rath.logger.impl.LogFactory;

public class MyTest {
	public static void main(String[] args) {
		Logger log = LogFactory.getLogger(MyTest.class);
		log.debug("tetetete");
		LogFactory.stopLoggerThreads();
	}
}
