package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivitySearchResultBinding;
import com.treecute.plant.model.Plant;
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
}
