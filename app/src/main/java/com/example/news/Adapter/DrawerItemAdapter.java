package com.example.news.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.Bean.DrawerContent;
import com.example.news.R;

import java.util.List;


/**
 * Created by bear on 2016/5/17.
 */
public class DrawerItemAdapter extends ArrayAdapter<DrawerContent> {
    private int resouceId;
    public DrawerItemAdapter(Context context, int resource, List<DrawerContent> objects) {
        super(context, resource,objects);
        resouceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerContent drawerContent=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resouceId, null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon=(ImageView) view.findViewById(R.id.id_item_icon);
            viewHolder.tv_title=(TextView) view.findViewById(R.id.id_item_title);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.iv_icon.setImageResource(drawerContent.getImageId());
        viewHolder.tv_title.setText(drawerContent.getName());
        return view;
    }
    class ViewHolder {
        ImageView iv_icon;
        TextView  tv_title;
    }


}
