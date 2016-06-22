package com.software.studio.delicacies.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.software.studio.delicacies.MainActivity;
import com.software.studio.delicacies.MapsActivity;
import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;
import com.software.studio.delicacies.data.SearchItem;
import com.software.studio.delicacies.data.SearchItemDAO;

import java.util.ArrayList;

public class Search extends Fragment implements View.OnClickListener {
    EditText editText;
    ImageButton button;
    RecyclerView.LayoutManager mLayoutManager;
    RecycleViewAdapter mAdapter;
    SearchItemDAO mySearchlog;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_recycler_view);

        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mySearchlog = new SearchItemDAO(getActivity().getApplicationContext());

        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
                intent.putExtra("LocationName", data);
                startActivity(intent);
            }
        });

        // Set text color
        if(MainActivity.getPref().getString("bgcolor", null) != null){
            TextView tv = (TextView) view.findViewById(R.id.search_history);
            tv.setTextColor(Color.WHITE);
        }

        editText = (EditText) view.findViewById(R.id.search_edittext);
        button = (ImageButton) view.findViewById(R.id.image_button_search);
        editText.setHint("  Please type keyword for searching");
        editText.setOnClickListener(this);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume()
    {
        Log.d("Call","On resume called");
        data.clear();
        ArrayList<SearchItem> SearchList = mySearchlog.getAll();
        for(SearchItem item : SearchList){
            data.add(item.getName());
        }
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }

    public void onClick(View v) {
        if(v == editText) {
            editText.getText().clear();
        }
        else if(v == button) {
            if(editText.getText().length()!=0)
            {
                String locationName = editText.getText().toString();
                Intent intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
                intent.putExtra("LocationName", locationName);
                SearchItem item = new SearchItem(0, locationName);
                mySearchlog.insert(item);
                startActivity(intent);
            }
            else{
                Toast.makeText(getActivity(), R.string.msg_InputIsEmpty, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
