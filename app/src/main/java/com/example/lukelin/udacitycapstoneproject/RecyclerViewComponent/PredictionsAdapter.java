package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;

import java.util.List;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsViewHolder>{

    private List<Predictions> predictionsList;
    public PredictionsAdapter(List<Predictions> predictionsList) {

        this.predictionsList = predictionsList;
    }

    @Override
    public PredictionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.predictions_item, viewGroup, false);
        return new PredictionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PredictionsViewHolder viewHolder, int i) {
        viewHolder.setData(predictionsList.get(i));
    }

    @Override
    public int getItemCount() {
        return predictionsList.size();
    }

}
