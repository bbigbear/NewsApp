package com.example.news.Model;

import android.content.Context;


import com.example.news.Api.FamousService;
import com.example.news.Bean.FamousInfo;
import com.example.news.Bean.FamousInfoReq;
import com.example.news.Http.RetrofitWrapper;

import retrofit2.Call;


/**
 * Created by bear on 2016/5/15.
 */
public class FamousInfoModel {
    private static  FamousInfoModel famousInfoModel;
    private FamousService famousService;

    private FamousInfoModel(Context context) {
        famousService = RetrofitWrapper.getInstance().create(FamousService.class);
    }

    //    单例模式
    public static FamousInfoModel getInstance(Context context){
        if(famousInfoModel==null){
            famousInfoModel=new FamousInfoModel(context);
        }
        return  famousInfoModel;
    }

//    查询名人名言
    public Call<FamousInfo> queryLookup(FamousInfoReq famousInfoReq){

        Call<FamousInfo> infoCall =famousService.getFamousResult(famousInfoReq.apikey,famousInfoReq.keyword,famousInfoReq.page,famousInfoReq.rows);
        return  infoCall;
    }

}
