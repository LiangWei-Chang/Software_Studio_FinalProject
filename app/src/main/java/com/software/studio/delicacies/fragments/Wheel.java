package com.software.studio.delicacies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.software.studio.delicacies.R;
import com.software.studio.delicacies.fragments.wheels.wheel_hsao_ze;
import com.software.studio.delicacies.fragments.wheels.wheel_water_wood;
import com.software.studio.delicacies.fragments.wheels.wheel_wind_cloud;

public class Wheel extends Fragment implements View.OnClickListener{
    ImageButton button_hsao_ze, button_water_wood, button_wind_cloud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wheel, container, false);
        button_hsao_ze = (ImageButton) view.findViewById(R.id.button_hsao_ze);
        button_water_wood = (ImageButton) view.findViewById(R.id.button_water_wood);
        button_wind_cloud = (ImageButton) view.findViewById(R.id.button_wind_cloud);

        button_hsao_ze.setOnClickListener(this);
        button_wind_cloud.setOnClickListener(this);
        button_water_wood.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == button_hsao_ze) {
            Intent intent = new Intent(getActivity().getApplicationContext(), wheel_hsao_ze.class);
            startActivity(intent);
        }
        else if(v == button_water_wood) {
            Intent intent = new Intent(getActivity().getApplicationContext(), wheel_water_wood.class);
            startActivity(intent);
        }
        else if(v == button_wind_cloud) {
            Intent intent = new Intent(getActivity().getApplicationContext(), wheel_wind_cloud.class);
            startActivity(intent);
        }
    }
}
