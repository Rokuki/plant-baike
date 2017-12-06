package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivitySearchResultBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.util.NoStatusBar;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.PlantListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkind on 2017/11/29 0029.
 */

public class SearchResultActivity extends AppCompatActivity {
    private List<Plant> plantList = new ArrayList<>();
    private String query;
    private RecyclerView recyclerView;
    private ActivitySearchResultBinding activitySearchResultBinding;
    private PlantListAdapter plantListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchResultBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_result);
        initPlantList();
        initView();
    }

    private void initView() {
        activitySearchResultBinding.toolbar.setTitle("\""+ query +"\""+ getString(R.string.search_hint));
        setSupportActionBar(activitySearchResultBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = activitySearchResultBinding.searchResultRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plantListAdapter = new PlantListAdapter();
        plantListAdapter.setPlantList(plantList);
        recyclerView.setAdapter(plantListAdapter);
        if (plantList == null) {
            activitySearchResultBinding.notFoundLayout.setVisibility(View.VISIBLE);
            activitySearchResultBinding.notFound.setText("找不到“" + query + "”");
            TextView recommand = activitySearchResultBinding.recommand;
            SpannableString spannableString = new SpannableString("试试简介搜索,可搜索地区、功效哦");
            MyClickableSpan clickableSpan = new MyClickableSpan();
            spannableString.setSpan(clickableSpan, 2, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            recommand.setMovementMethod(LinkMovementMethod.getInstance());
            recommand.setHighlightColor(getResources().getColor(R.color.colorPrimary));
            recommand.setText(spannableString);
        } else {
            activitySearchResultBinding.notFoundLayout.setVisibility(View.GONE);
        }

    }


    private void initPlantList() {
        Intent intent = getIntent();
        plantList = (List<Plant>) intent.getSerializableExtra("plantList");
        query = intent.getStringExtra("query");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            finish();
        }
    }
}
