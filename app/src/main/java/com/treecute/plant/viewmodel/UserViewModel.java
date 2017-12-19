package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.User;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.CartActivity;
import com.treecute.plant.view.CollectionsActivity;
import com.treecute.plant.view.LoginActivity;

import java.util.Observable;


/**
 * Created by mkind on 2017/11/21 0021.
 */

public class UserViewModel extends BaseObservable {
    public ObservableInt signAndLogin;
    public ObservableInt userVisibility;
    private User user;
    private Context context;


    public UserViewModel(@NonNull Context context) {
        this.context = context;
        signAndLogin = new ObservableInt(View.VISIBLE);
        userVisibility = new ObservableInt(View.GONE);
        Log.d(TAG.TAG, "UserViewModel: ");
    }

    public UserViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
        signAndLogin = new ObservableInt(View.GONE);
        userVisibility = new ObservableInt(View.VISIBLE);
    }

    public void loginAndSign(View view){
        Log.d(TAG.TAG, "loginAndSign: ");
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        Log.d(TAG.TAG, "loginAndSign: ");
    }

    public void cart(View view) {
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    public void collections(View view) {
        Intent intent = new Intent(context, CollectionsActivity.class);
        context.startActivity(intent);
    }

    public String getUserName(){
        return user.username;
    }
    public int getUserGender(){
        return user.gender;
    }
    public String getAvatar(){
        return user.avatar;
    }

    public int getId(){
        return user.id;
    }

    public String getEmail() {
        return user.email;
    }

    public String getPhone() {
        return user.phone;
    }

    public String getLastLoginTime() {
        return user.lastLoginTime;
    }

    public String getLastLoginIp() {
        return user.lastLoginIp;
    }

    public String getAccessToken() {
        return user.accessToken;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setUser(User user){
        this.user = user;
        notifyChange();
    }

}
