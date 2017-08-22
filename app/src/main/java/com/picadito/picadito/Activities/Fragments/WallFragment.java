package com.picadito.picadito.Activities.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.picadito.picadito.R;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class WallFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wall, container, false);

        return view;
    }
}

