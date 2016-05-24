package com.example.news.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.news.Bean.RealTimeNewInfoTwo;
import com.example.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by bear on 2016/5/23.
 */
public class DetailedNewsActivity extends AppCompatActivity {
    private ImageView imageview_news;
    private TextView textview_title;
    private TextView textview_content_detail;
    private ImageView imageView_news_url;
    private String url=null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscontent_detial_fragment);
        //初始化控件
        initview();
        //初始化事件
        initEvent();
        //点击事件
        onclick();

    }

    private void onclick() {
        imageView_news_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_webview=new Intent(DetailedNewsActivity.this,NewWebViewActivity.class);
                intent_webview.putExtra("url",url);
                startActivity(intent_webview);
            }
        });
    }


    private void initEvent() {
        //获取请求到的数据
        ArrayList<RealTimeNewInfoTwo.RetDataEntity> listNewInfo = (ArrayList<RealTimeNewInfoTwo.RetDataEntity>) getIntent().getSerializableExtra("content");
        Bundle bundle = this.getIntent().getExtras();
        int position=bundle.getInt("position");
        Log.i("i",listNewInfo.size()+"");
            Picasso.with(DetailedNewsActivity.this).load(listNewInfo.get(position).getImage_url()).into(imageview_news);
            textview_title.setText(listNewInfo.get(position).getTitle());
            textview_content_detail.setText(listNewInfo.get(position).getAbstract_content());
            url=listNewInfo.get(position).getUrl();
            Log.i("a",listNewInfo.get(position).getTitle());


    }

    private void initview() {
        imageview_news= (ImageView) findViewById(R.id.iv_newscontent);
        textview_title= (TextView) findViewById(R.id.tv_newscontent_title);
        textview_content_detail= (TextView) findViewById(R.id.tv_newscontent_detial);
        imageView_news_url= (ImageView) findViewById(R.id.iv_newscontent_url);
    }


}
