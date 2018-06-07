package wang.tyrael.task;

public interface AsyncListener {	
	void onSucceed();
	void onFail(int code, String msg);
}
