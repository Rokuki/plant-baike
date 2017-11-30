package com.treecute.plant.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.treecute.plant.view.fragment.BuyFragment;
import com.treecute.plant.view.fragment.SaleFragment;
import com.treecute.plant.view.fragment.SecondPageFragment;

/**
 * Created by mkind on 2017/11/29 0029.
 */

public class MarketFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    private Fragment buyFragment = null;
    private Fragment saleFragment = null;

    public MarketFragmentAdapter(FragmentManager fm) {
        super(fm);
        buyFragment = new BuyFragment();
        saleFragment = new SaleFragment();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case SecondPageFragment.PAGE_ONE:
                fragment = buyFragment;
                break;
            case SecondPageFragment.PAGE_TWO:
                fragment = saleFragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
