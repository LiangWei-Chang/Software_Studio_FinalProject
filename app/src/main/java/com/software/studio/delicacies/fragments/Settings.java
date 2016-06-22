package com.software.studio.delicacies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;

import java.util.ArrayList;

public class Settings extends Fragment {
    RecyclerView recyclerView;
    RecycleViewAdapter mAdapter;
    View menuView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);
        RecyclerView.LayoutManager mLayoutManager;
        ArrayList<String> data = new ArrayList<String>();
        data.add("Notifications");
        data.add("Change Background Color");
        data.add("Password");
        data.add("Reset");
        data.add("About");

        recyclerView = (RecyclerView) rootview.findViewById(R.id.setting_recycler_view);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        registerForContextMenu(recyclerView);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                menuView = view;
                if(data == mAdapter.getDataset().get(0) || data == mAdapter.getDataset().get(1) || data == mAdapter.getDataset().get(2)) {
                    recyclerView.showContextMenuForChild(view);
                }
                else if(data == mAdapter.getDataset().get(3)){

                }
                else if(data == mAdapter.getDataset().get(4)){

                }
            }
        });

        return rootview;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        if(menuView.getTag() == mAdapter.getDataset().get(0)) {
            menu.setHeaderTitle("Notifications");
            menu.add(0, v.getId(), 0, "On");
            menu.add(0, v.getId(), 0, "Off");
        }
        else if(menuView.getTag() == mAdapter.getDataset().get(1)) {
            menu.setHeaderTitle("Change Background Color");
            menu.add(1, v.getId(), 0, "Red");
            menu.add(1, v.getId(), 0, "Blue");
            menu.add(1, v.getId(), 0, "Green");
            menu.add(1, v.getId(), 0, "Orange");
            menu.add(1, v.getId(), 0, "Purple");
        }
        else if(menuView.getTag() == mAdapter.getDataset().get(2)) {
            menu.setHeaderTitle("Password");
            menu.add(2, v.getId(), 0, "On");
            menu.add(2, v.getId(), 0, "Off");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getGroupId()){
            case 0:
                if(item.getTitle().equals("On")){
                    Toast.makeText(getContext(), R.string.msg_NotificationOn, Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Off")){
                    Toast.makeText(getContext(), R.string.msg_NotificationOff, Toast.LENGTH_SHORT);
                }
                break;
            case 1:
                if(item.getTitle().equals("Red")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor + item.getTitle().toString(), Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Blue")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor + item.getTitle().toString(), Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Green")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor + item.getTitle().toString(), Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Orange")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor + item.getTitle().toString(), Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Purple")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor + item.getTitle().toString(), Toast.LENGTH_SHORT);
                }
                break;
            case 2:
                if(item.getTitle().equals("On")){
                    Toast.makeText(getContext(), "Password On", Toast.LENGTH_SHORT);
                }
                else if(item.getTitle().equals("Off")){
                    Toast.makeText(getContext(), "Password Off", Toast.LENGTH_SHORT);
                }
                break;
        }
        return true;
    }
}
