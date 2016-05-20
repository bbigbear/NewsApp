package com.example.news.UI;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.Adapter.NewContentAdapter;
import com.example.news.Api.Constant;
import com.example.news.Bean.NewsInfo;
import com.example.news.Bean.RealTimeNewInfoReqTwo;
import com.example.news.Bean.RealTimeNewInfoTwo;
import com.example.news.Model.RealTimeNewModelTwo;
import com.example.news.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by bear on 2016/5/17.
 */
public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewContentAdapter newContentAdapter;
    private RecyclerView.LayoutManager layoutMananger;

    private RealTimeNewModelTwo realTimeNewModelTwo;
    private String path = Environment.getExternalStorageDirectory().getPath() + "/"+"DrawAPicture";

    public static NewsFragment newInstance() {

       return new NewsFragment();
    }

    public NewsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.newsfragment,container,false);
        //初始化新闻modelTwo
        realTimeNewModelTwo=RealTimeNewModelTwo.getInstance(getActivity());
        recyclerView= (RecyclerView) view.findViewById(R.id.content_list);
        layoutMananger=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutMananger);

        //初始化新闻请求参数
        initNewParams();
        //获取数据
        loadNewsInfo();

        return view;
    }

    private void loadNewsInfo() {
        Call<RealTimeNewInfoTwo> callNews =realTimeNewModelTwo.getNewsInfoCall(initNewParams());
        callNews.enqueue(new Callback<RealTimeNewInfoTwo>() {
            @Override
            public void onResponse(Call<RealTimeNewInfoTwo> call, Response<RealTimeNewInfoTwo> response) {
                if(response.isSuccess()){
                    RealTimeNewInfoTwo result=response.body();
                    if(result!=null){
                        List<RealTimeNewInfoTwo.RetDataEntity> entity= result.getRetData();
                        
                        //加载数据到recycleView

                        ArrayList<NewsInfo> arrayList = new ArrayList<>();
                        for (int i=0;i<entity.size();i++){
                            arrayList.add(new NewsInfo(entity.get(i).getTitle(), path + "/" + "1.jpg"));
                        }
                        /*arrayList.add(new NewsInfo(entity.get(0).getTitle(), path + "/" + "1.jpg"));
                        arrayList.add(new NewsInfo(entity.get(1).getTitle(), path + "/" + "1.jpg"));
                        arrayList.add(new NewsInfo(entity.get(2).getTitle(), path + "/" + "1.jpg"));
                        arrayList.add(new NewsInfo(entity.get(3).getTitle(), path + "/" + "1.jpg"));
                        arrayList.add(new NewsInfo(entity.get(4).getTitle(), path + "/" + "1.jpg"));*/
                        newContentAdapter = new NewContentAdapter(arrayList, getActivity());
                        recyclerView.setAdapter(newContentAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<RealTimeNewInfoTwo> call, Throwable t) {
                Toast.makeText(getActivity(),"losefuck",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private RealTimeNewInfoReqTwo initNewParams() {
        RealTimeNewInfoReqTwo realTimeNewInfoReqTwo=null;
        realTimeNewInfoReqTwo=new RealTimeNewInfoReqTwo();
        realTimeNewInfoReqTwo.apikey= Constant.APIKEY;
        return  realTimeNewInfoReqTwo;
    }



}
