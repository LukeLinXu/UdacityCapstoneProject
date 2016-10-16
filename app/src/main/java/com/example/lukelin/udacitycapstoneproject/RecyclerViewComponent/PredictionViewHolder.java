package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Prediction;

import java.util.Date;

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
        final long epochTime = data.getEpochTime();
        updateCountDown(epochTime);
        time.setText(new Date(epochTime).toString());
        new CountDownTimer(DateUtils.HOUR_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountDown(epochTime);
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    private void updateCountDown(long epochTime){
        countDown.setText(DateUtils.formatElapsedTime((epochTime - System.currentTimeMillis())/1000));
    }
}
