package com.software.studio.delicacies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.software.studio.delicacies.fragments.Favorite;
import com.software.studio.delicacies.fragments.Search;
import com.software.studio.delicacies.fragments.Settings;
import com.software.studio.delicacies.fragments.Shouye;
import com.software.studio.delicacies.fragments.Wheel;

import java.util.ArrayList;
import java.util.List;

public class FragmentTagsActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout linear_shouye, linear_favorite, linear_search, linear_wheel, linear_settings;
    private ImageButton img_shouye, img_favorite, img_search, img_wheel, img_settings;
    private List<Fragment> fragments;
    private FragmentManager frmanager;
    private int selected_fragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framement_tags);
        fragments = new ArrayList<Fragment>();
        InitView();
    }

    private void InitView() {
        // 初始化 Tabs
        linear_shouye = (LinearLayout) findViewById(R.id.shouye);
        linear_favorite = (LinearLayout) findViewById(R.id.favorite);
        linear_search = (LinearLayout) findViewById(R.id.search);
        linear_wheel = (LinearLayout) findViewById(R.id.wheel);
        linear_settings = (LinearLayout) findViewById(R.id.settings);

        img_shouye = (ImageButton) findViewById(R.id.img_shouye);
        img_favorite = (ImageButton) findViewById(R.id.img_favorite);
        img_search = (ImageButton) findViewById(R.id.img_search);
        img_wheel = (ImageButton) findViewById(R.id.img_wheel);
        img_settings = (ImageButton) findViewById(R.id.img_settings);

        // 設置 Tabs 點擊事件
        linear_shouye.setOnClickListener(this);
        linear_favorite.setOnClickListener(this);
        linear_search.setOnClickListener(this);
        linear_wheel.setOnClickListener(this);
        linear_settings.setOnClickListener(this);

        // 初始化 Fragments
        Shouye shouye = new Shouye();
        Favorite favorite = new Favorite();
        Search search = new Search();
        Wheel wheel = new Wheel();
        Settings settings = new Settings();
        fragments.add(shouye);
        fragments.add(favorite);
        fragments.add(search);
        fragments.add(wheel);
        fragments.add(settings);

        frmanager = getSupportFragmentManager();

        FragmentTransaction beginTransaction = frmanager.beginTransaction();
        beginTransaction.add(R.id.fragment, shouye);
        beginTransaction.commit();
        ResetTabsImg();
        img_shouye.setImageResource(R.drawable.ic_menu_poi_on);
    }

    @Override
    public void onClick(View v) {
        // Tabs 點擊事件
        switch (v.getId()) {
            case R.id.shouye:
                if(selected_fragment!=0) {
                    ResetTabsImg();
                    SetTabsSelectedImg(0);
                }
                break;
            case R.id.favorite:
                if(selected_fragment!=1) {
                    ResetTabsImg();
                    SetTabsSelectedImg(1);
                }
                break;
            case R.id.search:
                if(selected_fragment!=2) {
                    ResetTabsImg();
                    SetTabsSelectedImg(2);
                }
                break;
            case R.id.wheel:
                if(selected_fragment!=3) {
                    ResetTabsImg();
                    SetTabsSelectedImg(3);
                }
                break;
            case R.id.settings:
                if(selected_fragment!=4) {
                    ResetTabsImg();
                    SetTabsSelectedImg(4);
                }
                break;
        }
    }

    private void SetTabsSelectedImg(int i){
        FragmentTransaction beginTransaction = frmanager.beginTransaction();
        beginTransaction.remove(fragments.get(selected_fragment));
        beginTransaction.add(R.id.fragment, fragments.get(i));

        selected_fragment = i;
        switch (i) {
            case 0:
                img_shouye.setImageResource(R.drawable.ic_menu_poi_on);
                break;
            case 1:
                img_favorite.setImageResource(R.drawable.ic_menu_favorite_on);
                break;
            case 2:
                img_search.setImageResource(R.drawable.ic_menu_search_on);
                break;
            case 3:
                img_wheel.setImageResource(R.drawable.ic_menu_wheel_on);
                break;
            case 4:
                img_settings.setImageResource(R.drawable.ic_menu_user_on);
                break;
        }

        beginTransaction.commit();
    }

    private void ResetTabsImg(){
        // 重置 Tabs_Img
        img_shouye.setImageResource(R.drawable.ic_menu_poi_off);
        img_favorite.setImageResource(R.drawable.ic_menu_favorite_off);
        img_search.setImageResource(R.drawable.ic_menu_search_off);
        img_wheel.setImageResource(R.drawable.ic_menu_wheel_off);
        img_settings.setImageResource(R.drawable.ic_menu_user_off);
    }
}
