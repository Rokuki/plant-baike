package com.treecute.plant.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.treecute.plant.fragment.HomePageFragment;
import com.treecute.plant.fragment.SecondPageFragment;
import com.treecute.plant.view.MainActivity;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    private Fragment homepageFragment = null;
    private Fragment secondpageFragment = null;


    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        homepageFragment = new HomePageFragment();
        secondpageFragment = new SecondPageFragment();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = homepageFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = secondpageFragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
