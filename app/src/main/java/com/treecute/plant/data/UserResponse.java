package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.User;

import java.util.List;

/**
 * Created by mkind on 2017/11/21 0021.
 */

class UserResponse {
    @SerializedName("data")private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
