package wang.tyrael.task;

public interface ProgressListener extends AsyncListener{
	void onProgress(int current, int total);
}
