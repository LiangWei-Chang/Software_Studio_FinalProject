package com.software.studio.delicacies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.software.studio.delicacies.R;

public class Wheel extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wheel, container, false);
        ImageView wheel = (ImageView)view.findViewById(R.id.img_wheels);
        wheel.setImageResource(R.drawable.wheel);
        return view;
    }
}
