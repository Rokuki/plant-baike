package com.treecute.plant.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.treecute.plant.R;
import com.treecute.plant.adapter.MainFragmentAdapter;
import com.treecute.plant.databinding.MenuLeftDrawerBinding;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.UserViewModel;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    private MainFragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    private RadioButton tab_home,tab_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        bindViews();
    }

    private void initView(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater layoutInflater = getLayoutInflater();
        View menu = layoutInflater.inflate(R.layout.menu_left_drawer,null);

        new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuView(menu)
                .inject();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.tab_rg);
        radioGroup.setOnCheckedChangeListener(this);
        tab_home = (RadioButton)findViewById(R.id.tab_home);
        tab_global = (RadioButton)findViewById(R.id.tab_global);
        TextView camera = (TextView) findViewById(R.id.camera);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "fonts/iconfont.ttf");    //加载iconfont
        tab_home.setTypeface(iconfont);
        tab_global.setTypeface(iconfont);
        camera.setTypeface(iconfont);
        fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());

        MenuLeftDrawerBinding menuLeftDrawerBinding = DataBindingUtil.bind(menu);
        User user = new User();
        user.setAvatar("http://treecute.com/images/avatar.gif");
        if (menuLeftDrawerBinding.getUserViewModel()==null){
            menuLeftDrawerBinding.setUserViewModel(new UserViewModel(user,MainActivity.this));
        }else {
            menuLeftDrawerBinding.getUserViewModel().setUser(user);
        }
    }

    private void bindViews() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.tab_home:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.tab_global:
                viewPager.setCurrentItem(PAGE_TWO);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    tab_home.setChecked(true);
                    break;
                case PAGE_TWO:
                    tab_global.setChecked(true);
            }
        }
    }
}
