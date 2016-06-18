package com.software.studio.delicacies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.software.studio.delicacies.R;

public class Search extends Fragment implements View.OnClickListener {
    EditText et;
    Button bt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search, container, false);
         et = (EditText) view.findViewById(R.id.search_edittext);
        bt = (Button) view.findViewById(R.id.button);
        et.setHint("Please type keyword for searching");
        et.setOnClickListener(this);
        bt.setOnClickListener(this);

        return view;
    }


    public void onClick(View v) {
        if(v == et)
        {
            et.getText().clear();
        }else if(v == bt)
        {
            Toast.makeText(getActivity(),et.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
