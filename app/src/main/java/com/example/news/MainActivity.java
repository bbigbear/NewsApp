package com.example.news;


import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.news.Adapter.DrawerItemAdapter;
import com.example.news.Bean.DrawerContent;
import com.example.news.UI.FamousFragment;
import com.example.news.UI.NewsFragment;
import com.example.news.UI.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DrawerContent> menu_list=new ArrayList<DrawerContent>();
    private ListView listView;
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    //定义fragment
    private NewsFragment mNewsFragment;
    private SettingFragment mSettingFragment;
    private FamousFragment mFamousFragment;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;


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
        fragmentManager = getFragmentManager();
        //默认显示新闻列表
        setItemSelection(0);

        //设置适配器
        DrawerItemAdapter adapter=new DrawerItemAdapter(MainActivity.this,R.layout.item_left_menu,menu_list);
        listView.setAdapter(adapter);
        //监听菜单
        listView.setOnItemClickListener(new DrawerItemClickListenerLeft());




    }

    private void setItemSelection(int i) {
        //开启一个事务
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        //隐藏所有的fragment，防止多个显示
        hideFragment(ft);
        switch (i){
            case 0:
                toolbar.setTitle("新闻列表");
                if(mNewsFragment==null){
                    mNewsFragment=new NewsFragment();
                    ft.add(R.id.id_content_container,mNewsFragment);
                }else {
                    ft.show(mNewsFragment);
                }
                break;
            case 1:
                toolbar.setTitle("名人名言");
                if(mFamousFragment==null){
                    mFamousFragment=new FamousFragment();
                    ft.add(R.id.id_content_container,mFamousFragment);
                }else {
                    ft.show(mFamousFragment);
                }
                break;
            case 2:
                toolbar.setTitle("设置");
                if (mSettingFragment==null){
                    mSettingFragment=new SettingFragment();
                    ft.add(R.id.id_content_container,mSettingFragment);
                }else {
                    ft.show(mSettingFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mNewsFragment!=null){
            transaction.hide(mNewsFragment);
        }
        if(mFamousFragment!=null){
            transaction.hide(mFamousFragment);
        }
        if (mSettingFragment!=null){
            transaction.hide(mSettingFragment);
        }
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
        DrawerContent news_item=new DrawerContent("新闻列表",R.mipmap.ic_news_icon);
        menu_list.add(news_item);
        DrawerContent my_item=new DrawerContent("名人名言",R.mipmap.ic_famous_icon);
        menu_list.add(my_item);
        DrawerContent setting_item=new DrawerContent("设置",R.mipmap.ic_my_icon);
        menu_list.add(setting_item);
    }

    private class DrawerItemClickListenerLeft implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            switch (position){
                case 0:
                    setItemSelection(0);
                    break;
                case 1:
                    setItemSelection(1);
                    break;
                case 2:
                    setItemSelection(2);
                    break;
                default:
                    break;
            }
            drawer_layout.closeDrawer(listView);

        }
    }



}
