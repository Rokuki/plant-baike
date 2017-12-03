package com.treecute.plant.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.treecute.plant.R;
import com.treecute.plant.databinding.FragmentSecondBinding;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.MarketFragmentAdapter;
import com.treecute.plant.viewmodel.SecondFragmentViewModel;


/**
 * Created by mkind on 2017/11/21 0021.
 */

public class SecondPageFragment extends Fragment implements ViewPager.OnPageChangeListener {
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    private MarketFragmentAdapter adapter;
    private ViewPager viewPager;
    private FragmentSecondBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        binding = DataBindingUtil.bind(view);
        SecondFragmentViewModel viewModel = new SecondFragmentViewModel(view.getContext());
        binding.setSecond(viewModel);
        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new MarketFragmentAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) view.findViewById(R.id.nts);
        navigationTabStrip.setTitles("购买", "出售");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setStripColor(getResources().getColor(R.color.colorPrimary));
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(getResources().getColor(R.color.black));
        navigationTabStrip.setActiveColor(getResources().getColor(R.color.colorPrimary));
        navigationTabStrip.setViewPager(viewPager);
        navigationTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG.TAG, "onPageScrolled: " + positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG.TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG.TAG, "onPageScrollStateChanged: " + state);
            }
        });
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.d(TAG.TAG, "onStartTabSelected: " + index);
            }

            @Override
            public void onEndTabSelected(String title, int index) {
                Log.d(TAG.TAG, "onStartTabSelected: " + index);
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
