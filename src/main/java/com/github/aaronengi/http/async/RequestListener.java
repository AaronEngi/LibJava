package com.github.aaronengi.http.async;

/**
 * Created by wangchao on 2017/3/7.
 */

public interface RequestListener {
    void onError(int codeLocal, String msg, ResponseData responseData);

    void onSuccess(ResponseData responseData);
}
