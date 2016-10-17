package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.Activity.StopDetailActvity;
import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;
import com.example.lukelin.udacitycapstoneproject.util.Extras;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class StopViewHolder extends RecyclerView.ViewHolder {
    private TextView title;

    public StopViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.stop_item_title);
    }

    public void setData(final Stop data){
        title.setText(data.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                context.startActivity(new Intent(context, StopDetailActvity.class).putExtra(Extras.DATA, String.valueOf(data.getStopId())));
            }
        });
    }
}
