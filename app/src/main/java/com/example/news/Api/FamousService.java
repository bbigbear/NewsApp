package com.example.news.Api;



import com.example.news.Bean.FamousInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by bear on 2016/5/15.
 */
public interface FamousService {
    @GET("/avatardata/mingrenmingyan/lookup")
    Call<FamousInfo> getFamousResult(@Header("apikey") String apikey,
                                     @Query("keyword") String keyword,
                                     @Query("page") int page,
                                     @Query("rows") int row);

}
