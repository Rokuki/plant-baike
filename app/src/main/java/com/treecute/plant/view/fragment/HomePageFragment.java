package com.treecute.plant.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.HomePageBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.util.ImagesLoader;
import com.treecute.plant.view.PlantDetailActivity;
import com.treecute.plant.view.adapter.CategoryAdapter;
import com.treecute.plant.view.adapter.PlantListAdapter;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;


/**
 * Created by mkind on 2017/11/21 0021.
 */

public class HomePageFragment extends Fragment {
    private HomePageBinding homePageBinding;
    private List<String> imgs = new ArrayList<>();
    private int bannerCount = 6;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView category_recyclerView;
    private RecyclerView glance_recyclerView;
    private CategoryAdapter categoryAdapter;
    private PlantListAdapter plantListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        homePageBinding = DataBindingUtil.bind(view);
        initBanner(view);
        initRecyclerView(view);
        getData(view);
        return view;
    }

    private void initRecyclerView(View view) {
        category_recyclerView = homePageBinding.categoryRecyclerView;
        glance_recyclerView = homePageBinding.glanceRecyclerView;
        category_recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        glance_recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        categoryAdapter = new CategoryAdapter();
        plantListAdapter = new PlantListAdapter();
        category_recyclerView.setAdapter(categoryAdapter);
        glance_recyclerView.setAdapter(plantListAdapter);
        glance_recyclerView.setNestedScrollingEnabled(false);
    }

    private void initBanner(final View view) {
        PlantApplication plantApplication = PlantApplication.create(view.getContext());
        PlantService plantService = plantApplication.getPlantService();

        Disposable disposable = plantService.fetchRandPlant(PlantFactory.RAND_PLANT_URL,bannerCount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantResponse>() {
                    @Override
                    public void accept(PlantResponse plantResponse) throws Exception {
                        final List<Plant> plantList = plantResponse.getPlantList();
                        for (Plant plant:plantList){
                            imgs.add(plant.getPicurl());
                        }
                        Banner banner = (Banner) view.findViewById(R.id.banner);
                        banner.setImageLoader(new ImagesLoader());
                        banner.setImages(imgs);
                        banner.setBannerAnimation(Transformer.Stack);
                        banner.setDelayTime(5000);
                        banner.start();
                        banner.setOnBannerClickListener(new OnBannerClickListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent = new Intent(view.getContext(),PlantDetailActivity.class);
                                intent.putExtra("plant",plantList.get(position-1));
                                view.getContext().startActivity(intent);
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(view.getContext(),throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getData(View view) {
        PlantApplication plantApplication = PlantApplication.create(view.getContext());
        PlantService plantService = plantApplication.getPlantService();
        Disposable disposable = plantService.fetchPlantCategory(PlantFactory.CATEGORY_LIST_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<List<PlantCategory>>() {
                    @Override
                    public void accept(List<PlantCategory> plantCategories) throws Exception {
                        categoryAdapter.setCategories(plantCategories);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: "+throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
        Disposable getPlantDisposable = plantService.fetchRandPlant(PlantFactory.RAND_PLANT_URL,20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantResponse>() {
                    @Override
                    public void accept(PlantResponse plantResponse) throws Exception {
                        plantListAdapter.setPlantList(plantResponse.getPlantList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: "+throwable.toString());
                    }
                });
        compositeDisposable.add(getPlantDisposable);
    }
}
