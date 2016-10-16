package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Prediction;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class PredictionViewHolder extends RecyclerView.ViewHolder {
    private TextView time;
    private TextView countDown;

    public PredictionViewHolder(View itemView) {
        super(itemView);
        countDown = (TextView) itemView.findViewById(R.id.prediction_item_countdown);
        time = (TextView) itemView.findViewById(R.id.prediction_item_time);
    }

    public void setData(Prediction data){
        countDown.setText(data.getMinutes()+"");
        time.setText(DateUtils.formatElapsedTime(data.getEpochTime()));
    }
}
