package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityPublishGoodsBinding;
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
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(PublishGoodsActivity.this, R.layout.activity_publish_goods);
        viewModel = new PublishGoodsViewModel(this);
        binding.setPublish(viewModel);
    }
}
