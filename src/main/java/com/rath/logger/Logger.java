package com.rath.logger;

public interface Logger {

	public void trace(String ...strings);
	
	public void debug(String ...strings);
	
	public void info(String ...strings);
	
	public void warn(String ...strings);
	
	public void error(String ...strings);
	
	public void fatal(String ...strings);
	
	public void warn(Throwable th, String ...strings);
	
	public void error(Throwable th, String ...strings);
	
	public void fatal(Throwable th, String ...strings);
	
}
