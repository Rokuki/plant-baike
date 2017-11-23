package com.treecute.plant.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecute.plant.R;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class HomePageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page,container,false);

        return view;
    }
}
