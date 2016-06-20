package com.software.studio.delicacies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.software.studio.delicacies.MapsActivity;
import com.software.studio.delicacies.R;

public class Favorite extends Fragment implements View.OnClickListener{
    ImageButton button;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        button = (ImageButton) view.findViewById(R.id.image_button_favorite);
        editText = (EditText) view.findViewById(R.id.favorite_edittext);
        editText.setHint("  Please type keyword add to favorite");
        editText.setOnClickListener(this);
        button.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        if(v == editText) {
            editText.getText().clear();
        }
        else if(v == button) {
            if(editText.getText().length()!=0)
            {
                Toast.makeText(getActivity(), editText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), R.string.msg_InputIsEmpty, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
