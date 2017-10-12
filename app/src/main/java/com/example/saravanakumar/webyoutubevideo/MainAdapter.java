package com.example.saravanakumar.webyoutubevideo;

import android.content.Context;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context activity;
    private String[] video;
    private LinearLayoutManager linearLayoutManager;

    public MainAdapter(Context activity, String[] video) {
        this.video = video;
        this.activity = activity;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video, viewGroup, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder viewHolder, final int position) {
        if(video[position] != null && video[position].length()>0) {
            viewHolder.mRecyclerView.setVisibility(View.VISIBLE);
            linearLayoutManager = new LinearLayoutManager(activity);
            viewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);

            //here recycler view is assigned as horizontal,
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.mRecyclerView.setHasFixedSize(true);

            // assigning the video url to web adapter
            WebAdapter webAdapter = new WebAdapter(activity,video[position]);

            //if we pass arraylist of url to web adapter,it display the first url to view, we can scroll horizontally to view others
            viewHolder.mRecyclerView.setAdapter(webAdapter);

        }else{
            viewHolder.mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return video.length;
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rvWebView);
        }
    }
}
