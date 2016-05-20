package com.example.news.Api;

import com.example.news.Bean.RealTimeNewInfo;
import com.example.news.Bean.RealTimeNewInfoTwo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * Created by bear on 2016/5/21.
 */
public interface RealTimeNewService_two {
    @GET("/songshuxiansheng/news/news")
    Call<RealTimeNewInfoTwo> getRealTimeNewTwoResult(@Header("apikey") String apikey);
}
