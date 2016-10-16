package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lukelin on 2016-10-14.
 */

public class StopDetailFragment extends ClickToRefreshFragmentBase<List<Predictions>> {

    private ArrayList<Prediction> predictions = new ArrayList<>();

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
        for(Predictions p : object){
            for(Direction d : p.getDirectionList()){
                predictions.addAll(d.getPredictionList());
            }
        }
        RecyclerView recyclerView = (RecyclerView) mainContent.findViewById(R.id.route_detail_fragment_directions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StickyTestAdapter stickyTestAdapter = new StickyTestAdapter(getActivity());
        StickyHeaderDecoration decor;
        decor = new StickyHeaderDecoration(stickyTestAdapter);
        recyclerView.setAdapter(stickyTestAdapter);
        recyclerView.addItemDecoration(decor);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.route_detail_fragment;
    }

    public class StickyTestAdapter extends RecyclerView.Adapter<StickyTestAdapter.ViewHolder> implements
            StickyHeaderAdapter<StickyTestAdapter.HeaderHolder> {

        private LayoutInflater mInflater;

        public StickyTestAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View view = mInflater.inflate(R.layout.prediction_item, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.time.setText(new Date(predictions.get(i).getEpochTime()).toString());
            viewHolder.countDown.setText(predictions.get(i).getMinutes()+"");
        }

        @Override
        public int getItemCount() {
            return predictions.size();
        }

        @Override
        public long getHeaderId(int position) {
            return predictions.get(position).getTitle().hashCode();
        }

        @Override
        public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
            final View view = mInflater.inflate(R.layout.prediction_header, parent, false);
            return new HeaderHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
            viewholder.tag.setText(predictions.get(position).getRouteTag());
            viewholder.header.setText(predictions.get(position).getTitle());
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView time;
            public TextView countDown;

            public ViewHolder(View itemView) {
                super(itemView);
                countDown = (TextView) itemView.findViewById(R.id.prediction_item_countdown);
                time = (TextView) itemView.findViewById(R.id.prediction_item_time);
            }
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            public TextView tag;
            public TextView header;
            public CheckBox favoriteCheckbox;

            public HeaderHolder(View itemView) {
                super(itemView);

                tag = (TextView) itemView.findViewById(R.id.prediction_header_tag);
                header = (TextView) itemView.findViewById(R.id.prediction_header_title);
                favoriteCheckbox = (CheckBox) itemView.findViewById(R.id.prediction_header_favorite_checkbox);
                favoriteCheckbox.setChecked(true);
            }
        }
    }
}
