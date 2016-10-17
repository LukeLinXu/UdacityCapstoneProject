package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Direction;

import java.util.List;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class RouteDetailDirectionAdapter extends RecyclerView.Adapter<RouteDetailDirectionHolder>{
    private List<Direction> directionList;

    public RouteDetailDirectionAdapter(List<Direction> directionList) {
        this.directionList = directionList;
    }

    @Override
    public RouteDetailDirectionHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.route_detail_direction_item, viewGroup, false);
        return new RouteDetailDirectionHolder(view);
    }

    @Override
    public void onBindViewHolder(RouteDetailDirectionHolder viewHolder, int i) {
        viewHolder.setData(directionList.get(i));
    }

    @Override
    public int getItemCount() {
        return directionList.size();
    }

}
