package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.pojos.Direction;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.example.lukelin.udacitycapstoneproject.pojos.PredictionsResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

import static android.R.attr.direction;

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
        recyclerView.setAdapter(new RecyclerView.Adapter<StopListViewHolder>() {
            @Override
            public StopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item, parent, false);
                return new StopListViewHolder(v);
            }

            @Override
            public void onBindViewHolder(StopListViewHolder holder, int position) {
                Predictions predictions = object.get(position);
                holder.mTag.setText(direction.getBranch());
                holder.mTitle.setText(direction.getTitle());
            }

            @Override
            public int getItemCount() {
                return object.getDirectionList().size();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.route_detail_fragment;
    }

    private class StopListViewHolder extends RecyclerView.ViewHolder{
        public TextView mTag, mTitle;

        public StopListViewHolder(View itemView) {
            super(itemView);
            mTag = (TextView) itemView.findViewById(R.id.route_list_item_tag);
            mTitle = (TextView) itemView.findViewById(R.id.route_list_item_title);
        }
    }
}
