package com.software.studio.delicacies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.studio.delicacies.DetailActivity;
import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;
import com.software.studio.delicacies.data.DelicaciesItem;
import com.software.studio.delicacies.data.ItemDAO;

import java.util.ArrayList;

public class Favorite extends Fragment{
    RecyclerView.LayoutManager mLayoutManager;
    RecycleViewAdapter mAdapter;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<DelicaciesItem> allList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.favorite_recycler_view);

        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        ItemDAO myFavoriteDelicacies = new ItemDAO(getActivity().getApplicationContext());
        allList = myFavoriteDelicacies.getAll();
        ArrayList<DelicaciesItem> myFavoriteList = myFavoriteDelicacies.getFavorite();

        for(DelicaciesItem item : myFavoriteList){
            data.add(item.getName() + " Rating : " + item.getRating());
        }

        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                int position = 0;
                for(int i=0; i<allList.size(); i++) {
                    if(data.equals(allList.get(i).getName() + " Rating : " + allList.get(i).getRating())) {
                        position = i;
                        break;
                    }
                }
                intent.putExtra("name", position);
                startActivity(intent);
            }
        });
        return rootview;
    }



}
