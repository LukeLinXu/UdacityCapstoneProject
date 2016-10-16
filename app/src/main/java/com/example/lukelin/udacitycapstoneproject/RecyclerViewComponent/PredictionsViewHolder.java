package com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.example.lukelin.udacitycapstoneproject.util.Utils;

/**
 * Created by LukeLin on 2016-10-16.
 */

public class PredictionsViewHolder extends RecyclerView.ViewHolder {
    private TextView tag;
    private TextView header;
    private CheckBox favoriteCheckbox;
    private RecyclerView recyclerView;

    public PredictionsViewHolder(View itemView) {
        super(itemView);
        tag = (TextView) itemView.findViewById(R.id.predictions_item_tag);
        header = (TextView) itemView.findViewById(R.id.predictions_item_route_title);
        favoriteCheckbox = (CheckBox) itemView.findViewById(R.id.predictions_item_favorite_checkbox);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.predictions_item_recyclerview);
    }

    public void setData(final Predictions data) {
        tag.setText(data.getRouteTag());
        header.setText(data.getRouteTitle());
        favoriteCheckbox.setChecked(Utils.hasFavorite(data.getFavoriteTag(), itemView.getContext().getContentResolver()));
        favoriteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Utils.buildBatchOperation(data.getFavoriteTag(), itemView.getContext().getContentResolver());
                }else {
                    Utils.buildBatchOperationDelete(data.getFavoriteTag(), itemView.getContext().getContentResolver());
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recyclerView.setAdapter(new DirectionAdapter(data.getDirectionList()));
    }
}
