package wang.tyrael.library.http;

public class UrlUtil {
	private static final String TAG = "UrlUtil";

	public static String getFileName(String url){
		int last = url.lastIndexOf("/");
		String name = url.substring(last + 1);
		//LogAdapter.d(TAG, name);
		return name;
	}
}
