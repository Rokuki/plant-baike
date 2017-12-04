package com.treecute.plant.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityPublishGoodsBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.User;
import com.treecute.plant.util.TAG;
import com.treecute.plant.viewmodel.PublishGoodsViewModel;

/**
 * Created by mkind on 2017/12/3 0003.
 */

public class PublishGoodsActivity extends AppCompatActivity {
    private ActivityPublishGoodsBinding binding;
    private PublishGoodsViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getUserData();
    }

    private void getUserData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("userJson", null);
        User user = gson.fromJson(userJson, User.class);
        viewModel.setUserName(user.getUsername());
        viewModel.setUserId(user.getId());
        viewModel.setToken(user.getAccessToken());
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(PublishGoodsActivity.this, R.layout.activity_publish_goods);
        viewModel = new PublishGoodsViewModel(this);
        binding.setPublish(viewModel);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.pickPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishGoodsActivity.this, PickPlantActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            Plant plant = (Plant) data.getSerializableExtra("plant");
            binding.pickPlant.setText("已选择：" + plant.name);
            viewModel.setSelectedPlantId(plant.id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }
}
