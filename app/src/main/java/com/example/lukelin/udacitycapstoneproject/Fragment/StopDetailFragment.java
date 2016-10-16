package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Direction;
import com.example.lukelin.udacitycapstoneproject.pojos.Prediction;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.example.lukelin.udacitycapstoneproject.pojos.PredictionsResult;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lukelin on 2016-10-14.
 */

public class StopDetailFragment extends ClickToRefreshFragmentBase<List<Predictions>> {

    @Override
    protected Observable<List<Predictions>> doRefresh() {
        return Observable.create(new Observable.OnSubscribe<List<Predictions>>() {
            @Override
            public void call(final Subscriber<? super List<Predictions>> subscriber) {
                String tag = getArguments().getString(Extras.DATA);
                RestClient.service.getPredictions("ttc", tag).enqueue(new Callback<PredictionsResult>() {
                    @Override
                    public void onResponse(Call<PredictionsResult> call, Response<PredictionsResult> response) {
                        subscriber.onNext(response.body().getPredictionsList());
                    }

                    @Override
                    public void onFailure(Call<PredictionsResult> call, Throwable t) {
                        subscriber.onError(t);
                    }
                });
            }
        });
    }

    @Override
    protected void refreshUI(RelativeLayout mainContent, final List<Predictions> object) {
        RecyclerView recyclerView = (RecyclerView) mainContent.findViewById(R.id.route_detail_fragment_directions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PredictionsAdapter predictionsAdapter = new PredictionsAdapter(object);
        recyclerView.setAdapter(predictionsAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.route_detail_fragment;
    }

    public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsHolder>{

        private List<Predictions> predictionsList;
        public PredictionsAdapter(List<Predictions> predictionsList) {

            this.predictionsList = predictionsList;
        }

        @Override
        public PredictionsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.predictions_item, viewGroup, false);
            return new PredictionsHolder(view);
        }

        @Override
        public void onBindViewHolder(PredictionsHolder viewHolder, int i) {
            viewHolder.setData(predictionsList.get(i));
        }

        @Override
        public int getItemCount() {
            return predictionsList.size();
        }

    }

    class PredictionsHolder extends RecyclerView.ViewHolder {
        private TextView tag;
        private TextView header;
        private CheckBox favoriteCheckbox;
        private RecyclerView recyclerView;

        public PredictionsHolder(View itemView) {
            super(itemView);
            tag = (TextView) itemView.findViewById(R.id.predictions_item_tag);
            header = (TextView) itemView.findViewById(R.id.predictions_item_route_title);
            favoriteCheckbox = (CheckBox) itemView.findViewById(R.id.predictions_item_favorite_checkbox);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.predictions_item_recyclerview);
        }

        public void setData(Predictions data) {
            tag.setText(data.getRouteTag());
            header.setText(data.getRouteTitle());
            favoriteCheckbox.setChecked(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(new DirectionAdapter(data.getDirectionList()));
        }
    }

    public class DirectionAdapter extends RecyclerView.Adapter<DirectionsHolder>{
        private List<Direction> directionList;

        public DirectionAdapter(List<Direction> directionList) {
            this.directionList = directionList;
        }

        @Override
        public DirectionsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.direction_item, viewGroup, false);
            return new DirectionsHolder(view);
        }

        @Override
        public void onBindViewHolder(DirectionsHolder viewHolder, int i) {
            viewHolder.setData(directionList.get(i));
        }

        @Override
        public int getItemCount() {
            return directionList.size();
        }

    }

    class DirectionsHolder extends RecyclerView.ViewHolder {
        private TextView header;
        private RecyclerView recyclerView;

        public DirectionsHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.direction_item_route_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.direction_item_recyclerview);
        }

        public void setData(Direction data) {
            header.setText(data.getTitle());
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(new PredictionAdapter(data.getPredictionList()));
        }
    }

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

    class PredictionViewHolder extends RecyclerView.ViewHolder {
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
}
