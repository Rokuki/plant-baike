package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchFilter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivitySearchBinding;
import com.treecute.plant.model.SearchSuggestions;
import com.treecute.plant.util.Permissions;
import com.treecute.plant.util.TAG;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding activitySearchBinding;
    private SearchView searchView;
    private List<SearchItem> suggestionsList = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private SearchHistoryTable mHistoryDatabase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PlantApplication plantApplication;
    private PlantService plantService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activitySearchBinding = DataBindingUtil.setContentView(SearchActivity.this,R.layout.activity_search);
        plantApplication = PlantApplication.create(SearchActivity.this);
        plantService = plantApplication.getPlantService();
        initSearchView();
        if (searchView!=null){
            searchView.open(true);
            initOnQueryTextListener();
            initSearchItemClick();
            initVoice();
        }
    }

    private void initVoice() {
        searchView.setOnVoiceIconClickListener(new SearchView.OnVoiceIconClickListener() {
            @Override
            public void onVoiceIconClick() {
                // permission
                Permissions.verifyRecordAudioPermissions(SearchActivity.this);
            }
        });
    }

    private void initOnQueryTextListener() {
        if (searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    //输入时候变化
                    List<Boolean> stateList = searchView.getFiltersStates();
                    if (!stateList.get(1)){
                        if (!newText.equals("") && newText!=null){
                            getSuggestions(newText);
                        }
                    }
                    searchAdapter.notifyDataSetChanged();
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //提交搜索
                    doSearch(query);
                    return true;
                }
            });
        }
    }
    private void initSearchItemClick() {
        searchAdapter.setOnSearchItemClickListener(new SearchAdapter.OnSearchItemClickListener() {
            @Override
            public void onSearchItemClick(View view, int position, String text) {
                //点击建议搜索项进行搜索
                doSearch(text);
                Log.d(TAG.TAG, "onSearchItemClick: "+text);
            }
        });
    }

    private void doSearch(final String query) {
        String searchPlace = "NAME";
        List<Boolean> stateList = searchView.getFiltersStates();
        if (stateList.get(0) && !stateList.get(1)){
            searchPlace = "NAME";
        }else if (!stateList.get(0) && stateList.get(1)){
            searchPlace = "SUMMARY";
        }
        Disposable disposable = plantService.getPlantBySearchPlace(PlantFactory.GET_PLANT_BY_SEARCH_PLACE,query,searchPlace)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantResponse>() {
                    @Override
                    public void accept(PlantResponse plantResponse) throws Exception {
                        searchView.close(true);
                        Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                        intent.putExtra("query",query);
                        intent.putExtra("plantList", (Serializable) plantResponse.getPlantList());
                        startActivity(intent);
                        Log.d(TAG.TAG, "accept: "+plantResponse.getPlantList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(SearchActivity.this,throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
        try{
            //添加进历史搜索
            mHistoryDatabase.addItem(new SearchItem(query));
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    private void getSuggestions(String newText) {
        Disposable disposable = plantService.getSearchSuggestionsList(PlantFactory.GET_SEARCH_SUGGESTIONS_LIST,newText)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<List<SearchSuggestions>>() {
                    @Override
                    public void accept(List<SearchSuggestions> searchSuggestionses) throws Exception {
                        suggestionsList.clear();
                        for (SearchSuggestions suggestions:searchSuggestionses){
                            suggestionsList.add(new SearchItem(suggestions.getName()));
                        }
                        searchAdapter.setData(suggestionsList);
                        searchAdapter.notifyDataSetChanged();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(SearchActivity.this,throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void initSearchView() {
        searchView = activitySearchBinding.searchView;
        searchView.setHint(R.string.search);
        mHistoryDatabase = new SearchHistoryTable(SearchActivity.this);
        mHistoryDatabase.setHistorySize(5); //历史搜索
        searchAdapter = new SearchAdapter(this,suggestionsList);
        searchView.setAdapter(searchAdapter);
        searchView.setVersionMargins(SearchView.VersionMargins.TOOLBAR_BIG);
        List<SearchFilter> filter = new ArrayList<>();
        filter.add(new SearchFilter("名称搜索", true));
        filter.add(new SearchFilter("简介搜索", false));
        searchView.setFilters(filter);
    }
}
