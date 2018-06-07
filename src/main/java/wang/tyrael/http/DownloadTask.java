package wang.tyrael.http;

import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadTask implements Runnable{
    public final String url;
    public final String localPath;

    public DownloadTask(String url, String localPath) {
        this.url = url;
        this.localPath = localPath;
    }

    @Override
    public void run() {
        downloadCaptcha();
    }

    private File downloadCaptcha(){
        //下载图片
        Response response = HttpDefault.get(url);
        File file = null;
        try {
            file = new File(localPath);
            OutputStream os = new FileOutputStream(file);
            os.write(response.body().bytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
