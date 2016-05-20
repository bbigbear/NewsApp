package com.example.news.Model;

import android.content.Context;

import com.example.news.Api.RealTimeNewService;
import com.example.news.Bean.RealTimeNewInfo;
import com.example.news.Bean.RealTimeNewInfoReq;
import com.example.news.Http.RetrofitWrapper;

import retrofit2.Call;

/**
 * Created by bear on 2016/5/19.
 */
public class RealTimeNewModel {
    private static RealTimeNewModel realTimeNewModel;
    private RealTimeNewService realTimeNewService;

    public RealTimeNewModel(Context context) {
        realTimeNewService= RetrofitWrapper.getInstance().create(RealTimeNewService.class);
    }
    //单例模式

    public static RealTimeNewModel getInstance(Context context){
        if(realTimeNewModel==null){
            realTimeNewModel=new RealTimeNewModel(context);
        }
        return realTimeNewModel;
    }

    //获取实时新闻
   public Call<RealTimeNewInfo> getInfoCall(RealTimeNewInfoReq realTimeNewInfoReq){
        Call<RealTimeNewInfo> infoCall=realTimeNewService.getRealTimeNewResult(realTimeNewInfoReq.apikey,realTimeNewInfoReq.keyword,realTimeNewInfoReq.page,realTimeNewInfoReq.count);
        return infoCall;
    }


}
