package com.treecute.plant.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.HomePageBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.view.ImagesLoader;
import com.treecute.plant.view.PlantDetail;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by mkind on 2017/11/21 0021.
 */

public class HomePageFragment extends Fragment {
    private HomePageBinding homePageBinding;
    private List<String> imgs = new ArrayList<>();
    private int bannerCount = 6;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page,container,false);
        DataBindingUtil.bind(view);
        initBanner(view);
        return view;
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
                                Gson gson = new Gson();
                                Intent intent = new Intent(view.getContext(),PlantDetail.class);
                                String plantJson = gson.toJson(plantList.get(position-1));
                                intent.putExtra("plant",plantJson);
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

}
