package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.data.PlantCollectionsResponse;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivityCollectionsBinding;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.SharedPreferencesHelper;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.CollectionsAdapter;
import com.treecute.plant.viewmodel.CollectionsViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CollectionsActivity extends AppCompatActivity {
    private ActivityCollectionsBinding binding;
    private RecyclerView recyclerView;
    private CollectionsAdapter collectionsAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initView();
        getData();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(CollectionsActivity.this, R.layout.activity_collections);
        CollectionsViewModel collectionsViewModel = new CollectionsViewModel(CollectionsActivity.this);
        binding.setCollection(collectionsViewModel);
    }

    private void initView() {
        binding.toolbar.setTitle("我的收藏");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionsActivity.this));
        collectionsAdapter = new CollectionsAdapter();
        recyclerView.setAdapter(collectionsAdapter);
    }

    private void getData() {
        User user = SharedPreferencesHelper.getUser(CollectionsActivity.this);
        PlantApplication plantApplication = PlantApplication.create(CollectionsActivity.this);
        PlantService plantService = plantApplication.getPlantService();

        Disposable disposable = plantService.queryCollections(PlantFactory.QUERY_COLLECTIONS, user.getAccessToken(), user.getUsername(), user.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantCollectionsResponse>() {
                    @Override
                    public void accept(PlantCollectionsResponse plantCollectionsResponse) throws Exception {
                        collectionsAdapter.setCollectionsList(plantCollectionsResponse.getCollectionsList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        compositeDisposable.add(disposable);
    }
}
