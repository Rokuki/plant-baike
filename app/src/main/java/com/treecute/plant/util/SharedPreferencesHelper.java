package com.treecute.plant.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.treecute.plant.model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mkind on 2017/12/6 0006.
 */

public class SharedPreferencesHelper {

    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("userJson", null);
        User user = gson.fromJson(userJson, User.class);
        return user;
    }

}
