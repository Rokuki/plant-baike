package com.treecute.plant.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.UserFactory;
import com.treecute.plant.data.UserService;
import com.treecute.plant.databinding.ActivityMainBinding;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.util.SetStatusbar;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.MainFragmentAdapter;
import com.treecute.plant.databinding.MenuLeftDrawerBinding;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.MainViewModel;
import com.treecute.plant.viewmodel.UserViewModel;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    private MainFragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    private RadioButton tab_home,tab_global;
    private boolean is_login = false;
    private MenuLeftDrawerBinding menuLeftDrawerBinding;
    private ActivityMainBinding activityMainBinding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        activityMainBinding.setMain(new MainViewModel(MainActivity.this));
        SetStatusbar.setMiuiStatusBarDarkMode(MainActivity.this,true);
        initView(savedInstanceState);
        bindViews();
        checkUserLoginStatus();
    }

    private void checkUserLoginStatus(){
        final SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        if (!is_login){
            //未确认，取存储看是否已经登录
            String access_token = sharedPreferences.getString("access_token",null);
            if (access_token != null) {
                Gson gson = new Gson();
                String userJson = sharedPreferences.getString("userJson", null);
                User user = gson.fromJson(userJson, User.class);
                PlantApplication plantApplication = PlantApplication.create(this);
                UserService userService = plantApplication.getUserService();
                Disposable disposable = userService.checkLogin(UserFactory.CHECK_LOGIN, access_token, user.getUsername())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(plantApplication.subscribeScheduler())
                        .subscribe(new Consumer<ResponseResult>() {
                            @Override
                            public void accept(ResponseResult responseResult) throws Exception {
                                is_login = responseResult.getMessage().equals("success");
                                isLogin(sharedPreferences);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d(com.treecute.plant.util.TAG.TAG, "accept: " + throwable.toString());
                            }
                        });
                compositeDisposable.add(disposable);
            }
        } else {
            isLogin(sharedPreferences);
        }
    }

    private void isLogin(SharedPreferences sharedPreferences) {
        if (is_login){
            Gson gson = new Gson();
            String userJson = sharedPreferences.getString("userJson",null);
            User user = gson.fromJson(userJson,User.class);
            UserViewModel userViewModel = new UserViewModel(user, MainActivity.this);
            menuLeftDrawerBinding.setUserViewModel(userViewModel);
        }else {
            User user = new User();
            UserViewModel userViewModel = new UserViewModel(MainActivity.this);
            userViewModel.setUser(user);
            menuLeftDrawerBinding.setUserViewModel(userViewModel);
        }
    }

    private void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent.getBooleanExtra("destroy_login",false)){
            is_login = true;
            LoginActivity.loginActivity.finish();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.search_action){
                    Intent intent1 = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(intent1);
                }
                return true;
            }
        });

        LayoutInflater layoutInflater = getLayoutInflater();
        View menu = layoutInflater.inflate(R.layout.menu_left_drawer,null);
        menuLeftDrawerBinding = DataBindingUtil.bind(menu);
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
