package com.quhaofeng.collweather.uitl;

/**
 * Created by Quhaofeng on 2016-4-3-0003.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
