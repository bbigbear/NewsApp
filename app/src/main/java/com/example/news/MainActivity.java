package com.example.news;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.news.Adapter.DrawerItemAdapter;
import com.example.news.Api.Constant;
import com.example.news.Bean.DrawerContent;
import com.example.news.Bean.FamousInfo;
import com.example.news.Bean.FamousInfoReq;
import com.example.news.Bean.RealTimeNewInfo;
import com.example.news.Bean.RealTimeNewInfoReq;
import com.example.news.Bean.RealTimeNewInfoReqTwo;
import com.example.news.Bean.RealTimeNewInfoTwo;
import com.example.news.Model.FamousInfoModel;
import com.example.news.Model.RealTimeNewModel;
import com.example.news.Model.RealTimeNewModelTwo;
import com.example.news.UI.FamousFragment;
import com.example.news.UI.MyFragment;
import com.example.news.UI.NewsFragment;
import com.example.news.UI.SettingFragment;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private List<DrawerContent> menu_list=new ArrayList<DrawerContent>();
    private ListView listView;
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    //定义fragment
    private NewsFragment mNewsFragment;
    private MyFragment mMyFragment;
    private SettingFragment mSettingFragment;
    private FamousFragment mFamousFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setContentView(R.layout.activity_main);


        //初始化菜单选项
        initMenu();
        //初始化toolbar
        initToolBar();

        //初始化控件
        initView();

        //设置适配器
        DrawerItemAdapter adapter=new DrawerItemAdapter(MainActivity.this,R.layout.item_left_menu,menu_list);
        listView.setAdapter(adapter);
        //监听菜单
        listView.setOnItemClickListener(new DrawerItemClickListenerLeft());




    }



    private void initToolBar() {
        toolbar= (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("新闻列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toc_white_24dp);

    }

    private void initView() {

        listView= (ListView) findViewById(R.id.id_list_left_drawer);
        drawer_layout= (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawer_layout, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawer_layout.setDrawerListener(mActionBarDrawerToggle);


    }

    private void initMenu() {
        DrawerContent news_item=new DrawerContent("新闻列表",R.drawable.new_select);
        menu_list.add(news_item);
        DrawerContent my_item=new DrawerContent("名人名言",R.drawable.my_select);
        menu_list.add(my_item);
        DrawerContent setting_item=new DrawerContent("设置",R.drawable.setting_select);
        menu_list.add(setting_item);
    }

    private class DrawerItemClickListenerLeft implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();

            switch (position){
                case 0:
                    toolbar.setTitle("新闻列表");
                    mNewsFragment=new NewsFragment();
                    ft.replace(R.id.id_content_container,mNewsFragment);
                    break;
                case 1:
                    toolbar.setTitle("名人名言");
                    mFamousFragment=new FamousFragment();
                    ft.replace(R.id.id_content_container,mFamousFragment);
                    break;
                case 2:
                    toolbar.setTitle("设置");
                    mSettingFragment=new SettingFragment();
                    ft.replace(R.id.id_content_container,mSettingFragment);
                    break;
                default:
                    break;
            }
            ft.commit();
            drawer_layout.closeDrawer(listView);

        }
    }



}
