package com.example.news.Api;

import com.example.news.Bean.RealTimeNewInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by bear on 2016/5/19.
 */
public interface RealTimeNewService {
    @GET("/songshuxiansheng/real_time/search_news")
    Call<RealTimeNewInfo> getRealTimeNewResult(@Header("apikey") String apikey,
                                               @Query("keyword") String keyword,
                                               @Query("page") int page,
                                               @Query("count") int count);

}
