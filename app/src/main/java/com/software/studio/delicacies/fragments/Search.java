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

public class Search extends Fragment implements View.OnClickListener {
    EditText editText;
    ImageButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        editText = (EditText) view.findViewById(R.id.search_edittext);
        button = (ImageButton) view.findViewById(R.id.image_button_search);
        editText.setHint("  Please type keyword for searching");
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
                Intent intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
                intent.putExtra("LocationName", editText.getText().toString());
                startActivity(intent);
            }
            else{
                Toast.makeText(getActivity(), R.string.msg_InputIsEmpty, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
