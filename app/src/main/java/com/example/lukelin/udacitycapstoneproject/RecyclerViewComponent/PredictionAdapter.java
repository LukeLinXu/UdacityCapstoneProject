package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Prediction;

import java.util.List;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class PredictionAdapter extends RecyclerView.Adapter<PredictionViewHolder>{

    private List<Prediction> predictionList;
    public PredictionAdapter(List<Prediction> predictionList) {

        this.predictionList = predictionList;
    }

    @Override
    public PredictionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prediction_item, viewGroup, false);
        return new PredictionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PredictionViewHolder viewHolder, int i) {
        viewHolder.setData(predictionList.get(i));
    }

    @Override
    public int getItemCount() {
        return predictionList.size();
    }

}
