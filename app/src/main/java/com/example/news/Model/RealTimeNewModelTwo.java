package com.example.news.Model;

import android.content.Context;
import com.example.news.Api.RealTimeNewService_two;
import com.example.news.Bean.RealTimeNewInfoReqTwo;
import com.example.news.Bean.RealTimeNewInfoTwo;
import com.example.news.Http.RetrofitWrapper;

import retrofit2.Call;


/**
 * Created by bear on 2016/5/21.
 */
public class RealTimeNewModelTwo {
    private static RealTimeNewModelTwo realTimeNewModelTwo;
    private RealTimeNewService_two realTimeNewService_two;

    public RealTimeNewModelTwo(Context context) {
        realTimeNewService_two= RetrofitWrapper.getInstance().create(RealTimeNewService_two.class);
    }
    //单例模式

    public static RealTimeNewModelTwo getInstance(Context context){
        if(realTimeNewModelTwo==null){
            realTimeNewModelTwo=new RealTimeNewModelTwo(context);
        }
        return realTimeNewModelTwo;
    }

    //获取实时新闻
    public Call<RealTimeNewInfoTwo> getNewsInfoCall(RealTimeNewInfoReqTwo realTimeNewInfoReqTwo){
        Call<RealTimeNewInfoTwo> newsinfoCall=realTimeNewService_two.getRealTimeNewTwoResult(realTimeNewInfoReqTwo.apikey);
        return newsinfoCall;
    }

}
