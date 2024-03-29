package com.treecute.plant;

import android.app.Application;
import android.content.Context;

import com.mikepenz.iconics.Iconics;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.data.UserFactory;
import com.treecute.plant.data.UserService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class PlantApplication extends Application {
    private PlantService plantService;
    private UserService userService;
    private GoodsService goodsService;
    private Scheduler scheduler;


    @Override
    public void onCreate() {
        super.onCreate();
        Iconics.init(getApplicationContext());
    }

    private static PlantApplication get(Context context){
        return (PlantApplication) context.getApplicationContext();
    }

    public static PlantApplication create(Context context){
        return PlantApplication.get(context);
    }


    public PlantService getPlantService(){
        if (plantService==null){
            plantService = PlantFactory.create();
        }
        return plantService;
    }

    public UserService getUserService(){
        if (userService==null){
            userService = UserFactory.create();
        }
        return userService;
    }

    public GoodsService getGoodsService() {
        if (goodsService == null) {
            goodsService = GoodsFactory.create();
        }
        return goodsService;
    }

    public Scheduler subscribeScheduler(){
        if (scheduler==null){
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
