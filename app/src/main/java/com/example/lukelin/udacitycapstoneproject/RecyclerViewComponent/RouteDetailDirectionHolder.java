package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Direction;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class RouteDetailDirectionHolder extends RecyclerView.ViewHolder {
    private TextView header;
    private RecyclerView recyclerView;

    public RouteDetailDirectionHolder(View itemView) {
        super(itemView);
        header = (TextView) itemView.findViewById(R.id.route_detail_direction_item_route_title);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.route_detail_direction_item_recyclerview);
    }

    public void setData(Direction data) {
        header.setText(data.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new StopAdapter(data.getStopList()));
    }
}
