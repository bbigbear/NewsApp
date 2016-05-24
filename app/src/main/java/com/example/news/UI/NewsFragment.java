package com.example.news.UI;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.io.Serializable;
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
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RealTimeNewModelTwo realTimeNewModelTwo;
    RealTimeNewInfoTwo result=new RealTimeNewInfoTwo();


    //错误照片的调用
    private String error_pic_url="http://img0.imgtn.bdimg.com/it/u=3982029889,2478115047&fm=21&gp=0.jpg";

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

        //初始化下拉刷新
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.content_swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.material_blue_700);

        //初始化新闻请求参数
        initNewParams();
        //获取数据
        loadNewsInfo();
        result.setRetData(result.getRetData());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            //加载数据到recycleView
                            final List<RealTimeNewInfoTwo.RetDataEntity> entity = result.getRetData();
                            ArrayList<NewsInfo> arrayList = new ArrayList<>();
                            final ArrayList<RealTimeNewInfoTwo.RetDataEntity> listContent=new ArrayList<RealTimeNewInfoTwo.RetDataEntity>();
                            for (int i = 0; i < entity.size(); i++) {
                                Log.i("eeeeeeeeeeeor",entity.get(i).getImage_url());
                                Log.i("eeeeeeeeeeeor2222",entity.get(i).getTitle());
                                if(entity.get(i).getImage_url().isEmpty()) {
                                    arrayList.add(new NewsInfo(entity.get(i).getTitle(), error_pic_url));
                                    listContent.add(new RealTimeNewInfoTwo.RetDataEntity(entity.get(i).getTitle(), entity.get(i).getUrl(),
                                            entity.get(i).getAbstract_content(), error_pic_url));
                                }else {
                                    arrayList.add(new NewsInfo(entity.get(i).getTitle(), entity.get(i).getImage_url()));
                                    listContent.add(new RealTimeNewInfoTwo.RetDataEntity(entity.get(i).getTitle(), entity.get(i).getUrl(),
                                            entity.get(i).getAbstract_content(), entity.get(i).getImage_url()));
                                }

                            }
                            newContentAdapter = new NewContentAdapter(arrayList, getActivity());
                            newContentAdapter.setItemClickListener(new NewContentAdapter.NewItemClickListener() {
                                @Override
                                public void setOnItemClick(View v, int position) {
                                    Intent intent=new Intent(getActivity(),DetailedNewsActivity.class);
                                    intent.putExtra("content",listContent);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("position",position);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            });
                            recyclerView.setAdapter(newContentAdapter);
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 5000);
            }

        });

        return view;
    }

    private void loadNewsInfo() {
        Call<RealTimeNewInfoTwo> callNews =realTimeNewModelTwo.getNewsInfoCall(initNewParams());
        callNews.enqueue(new Callback<RealTimeNewInfoTwo>() {
            @Override
            public void onResponse(Call<RealTimeNewInfoTwo> call, final Response<RealTimeNewInfoTwo> response) {
                if(response.isSuccess()){
                    result = response.body();
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
