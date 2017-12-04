package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivityPickPlantBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.view.adapter.PickPlantAdapter;
import com.treecute.plant.view.listener.OnPlantItemSelectListener;
import com.treecute.plant.viewmodel.PickPlantViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/12/3 0003.
 */

public class PickPlantActivity extends AppCompatActivity {
    private ActivityPickPlantBinding binding;
    private PickPlantViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private PickPlantAdapter adapter;
    private CompositeDisposable composite = new CompositeDisposable();
    private int resultCode = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(PickPlantActivity.this, R.layout.activity_pick_plant);
        viewModel = new PickPlantViewModel(this);
        binding.setPick(viewModel);
        binding.toolbar.setTitle("选择发布的植物");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new PickPlantAdapter();
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        searchView = binding.search;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PlantApplication app = PlantApplication.create(PickPlantActivity.this);
                PlantService service = app.getPlantService();
                Disposable disposable = service.getPlantBySearchPlace(PlantFactory.GET_PLANT_BY_SEARCH_PLACE, query, "NAME")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(app.subscribeScheduler())
                        .subscribe(new Consumer<PlantResponse>() {
                            @Override
                            public void accept(PlantResponse plantResponse) throws Exception {
                                adapter.setPlantList(plantResponse.getPlantList());
                                searchView.clearFocus();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
                composite.add(disposable);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        adapter.setOnPlantItemSelectListener(new OnPlantItemSelectListener() {
            @Override
            public void onPlantItemSelectListener(Plant plant) {
                Intent mIntent = new Intent();
                mIntent.putExtra("plant", plant);
                PickPlantActivity.this.setResult(resultCode, mIntent);
                PickPlantActivity.this.finish();
            }
        });
    }
}
