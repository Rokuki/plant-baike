package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityGoodsDetailBinding;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.ItemGoodsViewModel;

public class GoodsDetail extends AppCompatActivity {
    private ActivityGoodsDetailBinding detailBinding;
    private User seller;
    private Plant plant;
    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = DataBindingUtil.setContentView(GoodsDetail.this, R.layout.activity_goods_detail);
        getData();
        ItemGoodsViewModel viewModel = new ItemGoodsViewModel(this, goods, plant, seller);
        detailBinding.setDetail(viewModel);
    }

    private void getData() {
        Intent intent = getIntent();
        goods = (Goods) intent.getSerializableExtra("goods");
        seller = (User) intent.getSerializableExtra("user");
        plant = (Plant) intent.getSerializableExtra("plant");
    }
}
