package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.UserFactory;
import com.treecute.plant.data.UserService;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.MD5;
import com.treecute.plant.view.MainActivity;
import com.treecute.plant.view.SignUpActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/24 0024.
 */

public class LoginViewModel extends BaseObservable {
    private String username,password;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LoginViewModel(Context context) {
        this.context = context;
    }

    public void afterUsernameInput(Editable e){
        username = e.toString();
    }

    public void afterPasswordInput(Editable e){
        password = e.toString();
    }

    public void login(View view){
        String token = "treecute" + "Login" + "16.48" + username;//秘钥
        String md5_token = MD5.MD5Encode(token);
        PlantApplication application = PlantApplication.create(context);
        UserService userService = application.getUserService();
        Map<String,String> data = new HashMap<>();
        data.put("username",username);
        data.put("password",password);
        data.put("login_token",md5_token);
        Disposable disposable = userService.login(UserFactory.LOGIN_URL,data)
                .subscribeOn(application.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseResult<User>>() {
                    @Override
                    public void accept(ResponseResult<User> userResponseResult) throws Exception {
                        String message = userResponseResult.getMessage();
                        User user = userResponseResult.getData();
                        if (message.equals("SUCCESS")){
                            //登录成功
                            String access_token = userResponseResult.getData().getAccessToken();
                            Gson gson = new Gson();
                            String userJson = gson.toJson(user);
                            SharedPreferences shared = context.getSharedPreferences("user",Context.MODE_PRIVATE);
                            shared.edit().putString("access_token",access_token).apply();
                            shared.edit().putString("userJson",userJson).apply();
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("destroy_login",true);
                            context.startActivity(intent);
                        }else {
                            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(context,throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void onSignUpClick(View view) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }
}
