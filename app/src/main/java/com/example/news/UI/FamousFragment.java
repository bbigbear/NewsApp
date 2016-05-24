package com.example.news.UI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.Api.Constant;
import com.example.news.Bean.FamousInfo;
import com.example.news.Bean.FamousInfoReq;
import com.example.news.Model.FamousInfoModel;
import com.example.news.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bear on 2016/5/20.
 */
public class FamousFragment extends Fragment implements View.OnClickListener {

    private EditText et_famous_search;
    private ImageButton ib_famous_search;
    private TextView tv_famous_saying,tv_famous_name;

    private FamousInfoModel famousInfoModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.famousfragment, container, false);
        //初始化名人名言model
        famousInfoModel=FamousInfoModel.getInstance(getActivity());
        //初始化控件
        initViews(view);
        //初始化名人名言参数
        initFamousParmas();
        return view;

    }

    private FamousInfoReq initFamousParmas() {
        FamousInfoReq famousInfoReq=null;
        famousInfoReq=new FamousInfoReq();
        famousInfoReq.apikey= Constant.APIKEY;
        famousInfoReq.keyword=et_famous_search.getText().toString();
        famousInfoReq.page=3;
        famousInfoReq.rows=30;
        return famousInfoReq;
    }

    private void initViews(View view) {
        et_famous_search= (EditText) view.findViewById(R.id.edit_keyword);
        ib_famous_search= (ImageButton) view.findViewById(R.id.button_search);
        tv_famous_saying= (TextView) view.findViewById(R.id.famous_saying);
        tv_famous_name= (TextView) view.findViewById(R.id.famous_name);
        ib_famous_search.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_search:
                loadFamousIfo();
                break;
            default:
                break;
        }
    }

    private void loadFamousIfo() {
        Call<FamousInfo> callFamous =famousInfoModel.queryLookup(initFamousParmas());
        callFamous.enqueue(new Callback<FamousInfo>() {
            @Override
            public void onResponse(Call<FamousInfo> call, Response<FamousInfo> response) {
                if(response.isSuccess()){
                    FamousInfo result=response.body();
                    if(result!=null){
                        List<FamousInfo.ResultEntity> entity= result.getResult();
                            if(entity.size()!=0) {
                                tv_famous_saying.setText(entity.get(0).getFamous_saying() + "!");
                                tv_famous_name.setText("--" + entity.get(0).getFamous_name());
                                Log.i("1", "1" + entity.get(0).getFamous_saying() + "\n---" + entity.get(0).getFamous_name());
                            }else {
                                Toast.makeText(getActivity(),"没有相关名言，请重新输入关键字！",Toast.LENGTH_SHORT).show();
                            }

                    }
                }
            }

            @Override
            public void onFailure(Call<FamousInfo> call, Throwable t) {
                Log.i("lose",t.getCause()+"");
                Toast.makeText(getActivity(),"lose",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
