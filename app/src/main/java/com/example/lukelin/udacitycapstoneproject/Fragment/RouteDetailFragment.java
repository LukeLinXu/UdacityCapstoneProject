package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent.RouteDetailDirectionAdapter;
import com.example.lukelin.udacitycapstoneproject.pojos.Direction;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Route;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.RestClient;

import java.util.HashMap;
import java.util.List;

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
        HashMap<String, Stop> hashMap = new HashMap<>();
        for(Stop stop : object.getStopList()){
            hashMap.put(stop.getTag(), stop);
        }
        List<Direction> directionList = object.getDirectionList();
        for(Direction direction : directionList){
            for(Stop stop : direction.getStopList()){
                stop.setTitle(hashMap.get(stop.getTag()).getTitle());
                stop.setStopId(hashMap.get(stop.getTag()).getStopId());
            }
        }
        recyclerView.setAdapter(new RouteDetailDirectionAdapter(directionList));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.route_detail_fragment;
    }

}
