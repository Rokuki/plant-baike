package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.util.Log;
import android.view.View;

/**
 * Created by mkind on 2017/11/23 0023.
 */

public class SignUpViewModel extends BaseObservable {
    public ObservableField<String> hint;
    public ObservableInt hintVisibility;
    private String username;
    private String password;
    private String confirm;
    private Context context;

    public SignUpViewModel(Context context) {
        this.context = context;
        hintVisibility = new ObservableInt(View.GONE);
        hint = new ObservableField<>();
    }

    public void afterUsernameInput(Editable s){
        username = s.toString();
        if (username.isEmpty()){
            hintVisibility.set(View.VISIBLE);
            hint.set("用户名不可为空");
        }else if (username.length() > 12){
            hintVisibility.set(View.VISIBLE);
            hint.set("用户名不可超过12个字符");
        }else {
            hintVisibility.set(View.GONE);
        }
    }

    public void afterPasswordInput(Editable s){
        password = s.toString();
        if (password.isEmpty()){
            hintVisibility.set(View.VISIBLE);
            hint.set("密码不可为空");
        }else if (password.length() < 6){
            hintVisibility.set(View.VISIBLE);
            hint.set("密码最短为6个字符");
        } else if (password.length() > 16){
            hintVisibility.set(View.VISIBLE);
            hint.set("密码最多为16个字符");
        }else {
            hintVisibility.set(View.GONE);
        }
    }

    public void afterConfirmPasswordInput(Editable s){
        confirm = s.toString();
        if (!confirm.equals(password)){
            hintVisibility.set(View.VISIBLE);
            hint.set("密码不一致");
        }else {
            hintVisibility.set(View.GONE);
        }
    }

    public void sign_up(View view){


    }
}
