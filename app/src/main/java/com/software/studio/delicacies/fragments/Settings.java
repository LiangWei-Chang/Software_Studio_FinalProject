package com.software.studio.delicacies.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;

import java.util.ArrayList;

public class Settings extends Fragment {
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);
        RecyclerView.LayoutManager mLayoutManager;
        RecycleViewAdapter mAdapter;
        ArrayList<String> data = new ArrayList<String>();
        data.add("change background color");
        data.add("volume setting");


        recyclerView = (RecyclerView) rootview.findViewById(R.id.setting_recycler_view);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        registerForContextMenu(recyclerView);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                if(data.equals("change background color"))
                {
                    recyclerView.showContextMenuForChild(view);
                }
            }
        });

        return rootview;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Action 1") {
            Toast.makeText(getContext(),"Action 1",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle()=="Action 2"){
            Toast.makeText(getContext(),"Action 2",Toast.LENGTH_SHORT).show();
        }
        else {
            return false;
        }
        return true;
    }
}
