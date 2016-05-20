package com.example.news.Http;

import android.content.Context;

import com.example.news.Api.Constant;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by bear on 2016/5/19.
 */
public class RetrofitWrapper {
    private static RetrofitWrapper instance;
    private Context mContext;
    private Retrofit retrofit;

    public RetrofitWrapper() {
        retrofit=new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
//    单例模式

    public static RetrofitWrapper getInstance() {
        if(instance==null){
            synchronized (RetrofitWrapper.class){
                if (instance==null){
                    instance=new RetrofitWrapper();
                }
            }
        }
        return instance;
    }

    public <T> T create(final Class<T> service){
            return retrofit.create(service);
    }
}
