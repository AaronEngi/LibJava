package tyrael.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tyrael.DebugConfig;

public class LogAdapter {
	private static final DebugConfig sDebugConfig = DebugConfig.getInstance();
	private static final DateFormat sDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss SSS");

	/**
	 * 记录一个绝对不可能的事。
	 * 如果是可能的事，应该被处理；或者用w打印。
	 */
	public static void e(String tag, String content){
		if(sDebugConfig.isDebuggable()){
			throw new RuntimeException(format(tag, content));
		}else{
			System.out.println(format(tag, content));
		} 
	}

	public static void e(String tag, String content, Throwable e){
		e.printStackTrace();
		e(tag, content);
	}

	public static void w(String tag, String content){
		System.out.println(format(tag, content));
	}

	public static void w(String tag, String content, Throwable e){
		w(tag, content);
		e.printStackTrace();
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
