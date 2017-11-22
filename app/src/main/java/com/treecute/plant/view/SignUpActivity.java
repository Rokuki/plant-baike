package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.treecute.plant.R;
import com.treecute.plant.databinding.SignUpBinding;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.UserViewModel;

/**
 * Created by mkind on 2017/11/22 0022.
 */

public class SignUpActivity extends AppCompatActivity {
    String TAG = "CBC";
    private SignUpBinding signUpBinding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(signUpBinding.toolbar);
    }

    private void initDataBinding() {
        signUpBinding = DataBindingUtil.setContentView(this,R.layout.sign_up);
        userViewModel = new UserViewModel(this);
        signUpBinding.setUserViewModel(userViewModel);
    }
}
