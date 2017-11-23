package com.treecute.plant.viewmodel;

import android.content.Context;
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

import java.util.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class UserViewModel extends BaseObservable {
    private User user;
    private Context context;


    public UserViewModel(@NonNull Context context) {
        this.context = context;
    }

    public UserViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
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
