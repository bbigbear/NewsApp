package com.example.news.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.Bean.NewsInfo;
import com.example.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bear on 2016/5/18.
 */
public class NewContentAdapter extends RecyclerView.Adapter<NewContentAdapter.NewViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<NewsInfo> newsInfos;
    private Context mContext;


    public NewContentAdapter(ArrayList<NewsInfo> newsInfos, Context mContext) {
        this.newsInfos = newsInfos;
        this.mContext = mContext;
        this.layoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.newscontent_cardview,parent,false);
        return new NewViewHolder(
                view,
                (TextView) view.findViewById(R.id.news_content),
                (ImageView) view.findViewById(R.id.news_icon)
        );
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {
        NewsInfo newsInfo= newsInfos.get(position);
        holder.tv_news_content.setText(newsInfo.getNews_content());
        holder.iv_news_icon.setImageURI(Uri.parse(newsInfo.getNews_img()));

    }


    @Override
    public int getItemCount() {
        return newsInfos.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView tv_news_content;
        private ImageView iv_news_icon;

        public NewViewHolder(View itemView,TextView tv_news_content,ImageView iv_news_icon) {
            super(itemView);
            this.tv_news_content=tv_news_content;
            this.iv_news_icon=iv_news_icon;
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
