package com.software.studio.delicacies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private String[] mDataset;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    public RecycleViewAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
