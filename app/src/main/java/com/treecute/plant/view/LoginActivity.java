package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityLoginBinding;
import com.treecute.plant.viewmodel.LoginViewModel;

/**
 * Created by mkind on 2017/11/22 0022.
 */

public class LoginActivity extends AppCompatActivity {
    public static LoginActivity loginActivity;
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loginActivity = this;
        initDataBinding();
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        binding.setLogin(loginViewModel);
    }
}
