package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent.PredictionsAdapter;
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
        RecyclerView recyclerView = (RecyclerView) mainContent.findViewById(R.id.stop_detail_fragment_directions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PredictionsAdapter predictionsAdapter = new PredictionsAdapter(object);
        recyclerView.setAdapter(predictionsAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.stop_detail_fragment;
    }

}
