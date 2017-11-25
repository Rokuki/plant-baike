package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.IntegerResponse;
import com.treecute.plant.data.UserFactory;
import com.treecute.plant.data.UserService;
import com.treecute.plant.util.MD5;
import com.treecute.plant.view.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by mkind on 2017/11/23 0023.
 */

public class SignUpViewModel extends BaseObservable {
    public ObservableField<String> hint;
    private String username;
    private String password;
    private String confirm;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean username_valid = false,password_valid = false;


    public SignUpViewModel(Context context) {
        this.context = context;
        hint = new ObservableField<>();
    }

    public void afterUsernameInput(Editable s){
        username = s.toString();
        if (username.isEmpty()){
            hint.set("用户名不可为空");
        }else if (username.length() < 6){
            hint.set("用户名最短为6个字符");
        }
        else if (username.length() > 12){
            hint.set("用户名不可超过12个字符");
        }else {
            hint.set("");
            username_valid = true;
        }
    }

    public void afterPasswordInput(Editable s){
        password = s.toString();
        if (password.isEmpty()){
            hint.set("密码不可为空");
        }else if (password.length() < 6){
            hint.set("密码最短为6个字符");
        } else if (password.length() > 16){
            hint.set("密码最多为16个字符");
        }else {
            hint.set("");
        }
    }

    public void afterConfirmPasswordInput(Editable s){
        confirm = s.toString();
        if (!confirm.equals(password)){
            hint.set("密码不一致");
        }else {
            hint.set("");
            password_valid = true;
        }
    }

    public void sign_up(View view){

        if (username_valid&password_valid){
            String token = "treecute" + "POST" + "sign_up" + "1748" + username;//秘钥
            String md5_token = MD5.MD5Encode(token);
            PlantApplication plantApplication = PlantApplication.create(context);
            UserService userService = plantApplication.getUserService();
            Map<String,String> data = new HashMap<>();
            data.put("username",username);
            data.put("password",password);
            data.put("user_token",md5_token);
            Log.d(TAG, "sign_up: "+md5_token);
            Disposable disposable = userService.signUp(UserFactory.SIGN_UP_URL,data)
                    .subscribeOn(plantApplication.subscribeScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<IntegerResponse>() {
                        @Override
                        public void accept(IntegerResponse integerResponse) throws Exception {
                            if (integerResponse.getStatus()==1){
                                Toast.makeText(context,"注册成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.putExtra("username",username);
                                context.startActivity(intent);
                            }else {
                                Toast.makeText(context,"注册失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(TAG, throwable.toString());
                        }
                    });
            compositeDisposable.add(disposable);
        }else {
            Toast.makeText(context,"信息有误，请检查无误后再次提交",Toast.LENGTH_SHORT).show();
        }


    }
}
