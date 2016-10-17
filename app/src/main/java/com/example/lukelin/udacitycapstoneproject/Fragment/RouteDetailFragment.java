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
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Route;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lukelin on 2016-10-14.
 */

public class RouteDetailFragment extends ClickToRefreshFragmentBase<Route> {

    @Override
    protected Observable<Route> doRefresh() {
        return Observable.create(new Observable.OnSubscribe<Route>() {
            @Override
            public void call(final Subscriber<? super Route> subscriber) {
                String tag = getArguments().getString(Extras.DATA);
                RestClient.service.getRouteDetail("ttc", tag).enqueue(new Callback<GetRouteResult>() {
                    @Override
                    public void onResponse(Call<GetRouteResult> call, Response<GetRouteResult> response) {
                        subscriber.onNext(response.body().getRoute());
                    }

                    @Override
                    public void onFailure(Call<GetRouteResult> call, Throwable t) {
                        subscriber.onError(t);
                    }
                });
            }
        });
    }

    @Override
    protected void refreshUI(RelativeLayout mainContent, final Route object) {
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
                Direction direction = object.getDirectionList().get(position);
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
        public TextView mTitle;

        public StopListViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.route_list_item_title);
        }
    }
}
