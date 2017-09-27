package cn.tyrael.library.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.tyrael.library.DebugConfig;

public class LogAdapter {
	private static final DebugConfig sDebugConfig = DebugConfig.getInstance();
	private static final DateFormat sDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss SSS");
	
	public static void e(String tag, String content){
		if(sDebugConfig.isDebuggable()){
			throw new RuntimeException(format(tag, content));
		}else{
			
		} 
	}
	
	public static void w(String tag, String content){
		System.out.println(format(tag, content));
	}
	
	public static void i(String tag, String content){
		System.out.println(format(tag, content));
	}
	
	public static void d(String tag, String content){
		if(sDebugConfig.isDebuggable()){
			System.out.println(format(tag, content));
		}else{
			
		} 
	}
	
	private static String format(String tag, String content){
		return sDateFormat.format(new Date()) + ":" +tag + ":" +  content;
	}
}
