package com.rath.logger;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	
	private Map<String, String> map;
	
	private static Configuration config;

	private Configuration() {
		super();
		map = new HashMap<String, String>();
		// Read it from a config file or similar
	}
	
	public static Configuration getInstance() {
		if (null == config) {
			synchronized (Configuration.class) {
				config = new Configuration();
			}
		}
		return config;
	}
	
	public String get(String key, String defaultValue) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			return defaultValue;
		}
	}

}
