package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;

import java.util.List;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class StopAdapter extends RecyclerView.Adapter<StopViewHolder>{

    private List<Stop> stopList;
    public StopAdapter(List<Stop> stopList) {
        this.stopList = stopList;
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stop_item, viewGroup, false);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StopViewHolder viewHolder, int i) {
        viewHolder.setData(stopList.get(i));
    }

    @Override
    public int getItemCount() {
        return stopList.size();
    }

}
