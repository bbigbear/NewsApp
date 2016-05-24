package com.example.news.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.news.R;

/**
 * Created by bear on 2016/5/24.
 */
public class NewWebViewActivity extends AppCompatActivity {
    private WebView webView_news;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_webview_activity);
        String url=getIntent().getStringExtra("url");
        webView_news= (WebView) findViewById(R.id.webview_newscontent);
        webView_news.getSettings().setJavaScriptEnabled(true);
        webView_news.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView_news.loadUrl(url);
    }
}
