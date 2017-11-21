package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.treecute.plant.R;
import com.treecute.plant.databinding.MenuLeftDrawerBinding;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.UserViewModel;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class MainActivity extends AppCompatActivity {

    private SlidingRootNav slidingRootNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater layoutInflater = getLayoutInflater();
        View menu = layoutInflater.inflate(R.layout.menu_left_drawer,null);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuView(menu)
                .inject();
        MenuLeftDrawerBinding menuLeftDrawerBinding = DataBindingUtil.bind(menu);
        User user = new User();
        user.setAvatar("http://treecute.com/images/avatar.gif");
        if (menuLeftDrawerBinding.getUserViewModel()==null){
            menuLeftDrawerBinding.setUserViewModel(new UserViewModel(user,MainActivity.this));
        }else {
            menuLeftDrawerBinding.getUserViewModel().setUser(user);
        }
    }
}
