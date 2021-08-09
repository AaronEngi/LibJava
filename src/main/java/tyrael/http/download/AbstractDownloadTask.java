package tyrael.http.download;

/**
 * 封装一个下载任务
 *
 * Created by wangchao on 2017/2/28.
 */

public abstract class AbstractDownloadTask {
    public final String destPath;
    public final String srcUrl;
    public final DownLoadListener listener;

    protected AbstractDownloadTask(String srcUrl, String destPath, DownLoadListener listener) {
        this.destPath = destPath;
        this.srcUrl = srcUrl;
        this.listener = listener;
    }

    public abstract void run();
}
