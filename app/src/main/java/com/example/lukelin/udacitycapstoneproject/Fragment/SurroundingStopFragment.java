package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.Activity.StopDetailActvity;
import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.data.StopColumns;
import com.example.lukelin.udacitycapstoneproject.data.StopProvider;
import com.example.lukelin.udacitycapstoneproject.util.Extras;

/**
 * Created by lukelin on 2016-10-14.
 */

public class SurroundingStopFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int CURSOR_LOADER_ID = 0;
    private RecyclerView recyclerView;
    private double lat = 43.7967125;
    private double lon = -79.312078;
    private double range = 0.005;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerview);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(getActivity(), StopProvider.Stops.CONTENT_URI,
//                new String[]{StopColumns.TAG, StopColumns.TITLE, StopColumns.LAT, StopColumns.LON},
//                StopColumns.LAT + " < ? AND "+ StopColumns.LON + " < ?",
//                new String[]{"43.645615", "-79.3862538"},
//                null);

        String latRang = (lat - range) + " AND " + (lat + range);
        String lonRang = (lon - range) + " AND " + (lon + range);
        return new CursorLoader(getActivity(), StopProvider.Stops.CONTENT_URI,
                new String[]{StopColumns.TAG, StopColumns.TITLE, StopColumns.LAT, StopColumns.LON, StopColumns.ID},
                "( "+StopColumns.LAT+" BETWEEN "+latRang+")"+
                        " AND "+
                        "( "+StopColumns.LON+" BETWEEN "+lonRang+")",
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        Log.d("Luke", "LukeonLoadFinished: "+data.getCount());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<StopListViewHolder>() {
            @Override
            public StopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item, parent, false);
                return new StopListViewHolder(v);
            }

            @Override
            public void onBindViewHolder(StopListViewHolder holder, int position) {
                if(data.moveToPosition(position)){
                    holder.mTag.setText(data.getString(data.getColumnIndex(StopColumns.TAG)));
                    holder.mTitle.setText(data.getString(data.getColumnIndex(StopColumns.TITLE)));
                    final String stopId = data.getString(data.getColumnIndex(StopColumns.ID));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            startActivity(new Intent(getActivity(), MapActivity.class));
                            startActivity(new Intent(getActivity(), StopDetailActvity.class).putExtra(Extras.DATA, stopId));
                        }
                    });
                }
            }

            @Override
            public int getItemCount() {
                return data.getCount();
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

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
