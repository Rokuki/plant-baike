package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivitySignUpBinding;
import com.treecute.plant.viewmodel.SignUpViewModel;

/**
 * Created by mkind on 2017/11/22 0022.
 */

public class SignUpActivity extends AppCompatActivity {
    String TAG = "CBC";
    private ActivitySignUpBinding signUpBinding;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDataBinding();
        setSupportActionBar(signUpBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDataBinding() {
        signUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        signUpViewModel = new SignUpViewModel(this);
        signUpBinding.setSignUpViewModel(signUpViewModel);
    }
}
