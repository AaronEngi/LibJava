package tyrael.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tyrael.http.url.UrlParser;
import tyrael.log.LogAdapter;

/**
 * 封装底层框架
 */
public class HttpAdapter {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	protected static final String TAG = "HttpAdapter";
	private OkHttpClient client;

	public void setClient(OkHttpClient client) {
		this.client = client;
	}

	public Response get(String url) {
		Request request = new Request.Builder()
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
				.url(url)
				.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			LogAdapter.w(TAG, "请求失败：" + url);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return response;
	}

	public Response post(String url){
		return this.post(url, new HashMap<>());
	}

	public Response post(String url, String str){
		RequestBody body = RequestBody.create(null, str);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			// return response.body().string();
			// TODO 判断是否登录成功
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public Response postJson(String url, String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			// return response.body().string();
			// TODO 判断是否登录成功
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public Response post(String url, String json, Map<String, String> header) {
		RequestBody body = RequestBody.create(JSON, json);
		Request.Builder builder =new Request.Builder().url(url).post(body);
        header.entrySet().stream().forEach(entry->builder.addHeader(entry.getKey(), entry.getValue()));
		Request request = builder.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			// return response.body().string();
			// TODO 判断是否登录成功
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public Response post(String url, Map<String, String> params) {
		FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
//			 if (TextUtils.isEmpty(entry.getKey())) {
//			 LogHelper.d(TAG, "TextUtils.isEmpty(entry.getKey()");
//			 continue;
//			 }
//			 if (TextUtils.isEmpty(entry.getValue())) {
//			 LogHelper.d(TAG, "TextUtils.isEmpty(entry.getValue())");
//			 continue;
//			 }

			mFormBodyBuilder.add(entry.getKey(), entry.getValue());
			// logParams += entry.getKey() + "=" + entry.getValue() + ",";
		}
		// LogHelper.i(TAG, url + "?uid=" + LoginManager.TOKEN + ",PARAMS:" +
		// logParams);
		RequestBody requestBody = mFormBodyBuilder.build();

		Request request = new Request.Builder().url(url)
				.post(requestBody).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			// return response.body().string();
			// TODO 判断是否登录成功
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 下载文件
	 *
	 * @param fileUrl
	 *            文件url
	 * @param destFileDir
	 *            存储目标目录
	 */
	public void downloadFile(String fileUrl, final String destFileDir) {
		final File file = new File(destFileDir);
		final Request request = new Request.Builder().url(fileUrl).build();
		Call c =null;
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				LogAdapter.e(TAG, "下载失败");
				// if (e.getCause().equals(SocketTimeoutException.class)) {
				// sendFailCallback(callBack, new Exception("连接超时，请稍后重试."));
				// } else {
				// sendFailCallback(callBack, e);
				// }
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				InputStream is = null;
				byte[] buf = new byte[2048];
				int len = 0;
				FileOutputStream fos = null;
				try {
					long total = response.body().contentLength();
					//LogAdapter.d(TAG, "total------>" + total);
					long current = 0;
					is = response.body().byteStream();
					fos = new FileOutputStream(file);
					while ((len = is.read(buf)) != -1) {
						float currentProgress = current * 100 / total;
						current += len;
						fos.write(buf, 0, len);
						float readedSize = current * 100 / total;
						//LogAdapter.d(TAG, "current------>" + readedSize + "|" + currentProgress);

						// if (readedSize - currentProgress >= 1)
						// callBack.onProgress(total, current);
					}
					fos.flush();
					// sendSuccessCallBack(callBack, (T) file);
				} catch (IOException e) {
					LogAdapter.e(TAG, e.toString());
					// sendFailCallback(callBack, e);
				} finally {
					try {
						if (is != null) {
							is.close();
						}
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						LogAdapter.e(TAG, e.toString());
					}
				}
			}
		});
	}

	public void download(String dir, List<String> urlList) {
		for (String url : urlList) {
			UrlParser urlParser = new UrlParser(url);
			String file = dir + urlParser.getFileName();
			downloadFile(url, file);
		}
	}

	/**
	 * 下载文件
	 *
	 * @param fileUrl
	 *            文件url
	 * @param destFileDir
	 *            存储目标目录
	 */
	public void downloadSync(String fileUrl, final String destFileDir) {
		final File file = new File(destFileDir);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		final Request request = new Request.Builder().url(fileUrl).build();
		try {
			Response response = client.newCall(request).execute();
			InputStream is = null;
			byte[] buf = new byte[2048];
			int len = 0;
			FileOutputStream fos = null;
			try {
				long total = response.body().contentLength();
				LogAdapter.d(TAG, "total------>" + total);
				long current = 0;
				is = response.body().byteStream();
				fos = new FileOutputStream(file);
				while ((len = is.read(buf)) != -1) {
					float currentProgress = current * 100 / total;
					current += len;
					fos.write(buf, 0, len);
					float readedSize = current * 100 / total;
//					LogAdapter.d(TAG, "current------>" + readedSize + "|" + currentProgress);

					// if (readedSize - currentProgress >= 1)
					// callBack.onProgress(total, current);
				}
				fos.flush();
				// sendSuccessCallBack(callBack, (T) file);
			} catch (IOException e) {
				LogAdapter.e(TAG, e.toString());
				// sendFailCallback(callBack, e);
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					LogAdapter.e(TAG, e.toString());
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void downloadSync(String dir, List<String> urlList) {
		for (String url : urlList) {
			UrlParser urlParser = new UrlParser(url);
			String file = dir + File.separator + urlParser.getFileName();
			LogAdapter.d(TAG, url);
			LogAdapter.d(TAG, file);
			downloadSync(url, file);
		}
	}

	public OkHttpClient getClient() {
		return client;
	}
}
