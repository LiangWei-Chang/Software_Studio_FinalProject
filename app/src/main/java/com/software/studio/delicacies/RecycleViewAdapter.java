package com.software.studio.delicacies;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> implements View.OnClickListener{
    private ArrayList<String> mDataset;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private SharedPreferences settings;

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    // Define Interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    public RecycleViewAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        // Read Text Color
        settings = MainActivity.getPref();
        if(settings.getString("bgcolor", null) != null){
            v.setTextColor(Color.WHITE);
        }
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.itemView.setTag(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void addItem(String text) {
        mDataset.add(text);
        this.notifyDataSetChanged();
    }

    public ArrayList<String> getDataset(){
        return mDataset;
    }
}
