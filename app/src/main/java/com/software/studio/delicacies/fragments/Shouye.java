package com.software.studio.delicacies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.studio.delicacies.AddDataActivity;
import com.software.studio.delicacies.DetailActivity;
import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;
import com.software.studio.delicacies.data.DelicaciesItem;
import com.software.studio.delicacies.data.ItemDAO;

import java.util.ArrayList;

public class Shouye extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView.LayoutManager mLayoutManager;
        final RecycleViewAdapter mAdapter;
        ArrayList<String> data = new ArrayList<>();
        FloatingActionButton fab;

        ItemDAO myDelicacies = new ItemDAO(getActivity().getApplicationContext());
        Log.d("count", new Integer(myDelicacies.getCount()).toString());
        if(myDelicacies.getCount() == 0){
            Log.d("sample","sample insert");
            myDelicacies.ConstructDelicacies();
        }
        ArrayList<DelicaciesItem> DelicaciesList = myDelicacies.getAll();
        for(DelicaciesItem item : DelicaciesList){
            data.add(item.getName() + " Rating : " + item.getRating());
        }

        View rootView = inflater.inflate(R.layout.fragment_shouye, container, false);

        // Get a reference to RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.shouye_recycler_view);

        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                int position=0;
                for(int i=0;i<mAdapter.getDataset().size();i++)
                {
                    if(data.equals(mAdapter.getDataset().get(i))) {
                        position = i;
                        break;
                    }
                }
                intent.putExtra("name", position);
                startActivity(intent);
            }
        });

        fab = (FloatingActionButton) rootView.findViewById(R.id.floating_action_button);
        assert fab!=null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddDataActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
