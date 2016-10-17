package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.app.ActivityOptions;
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

import com.example.lukelin.udacitycapstoneproject.Activity.RouteDetailActvity;
import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.data.RouteColumns;
import com.example.lukelin.udacitycapstoneproject.data.RouteProvider;
import com.example.lukelin.udacitycapstoneproject.util.Extras;

/**
 * Created by lukelin on 2016-10-14.
 */

public class RouteListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int CURSOR_LOADER_ID = 0;
    private RecyclerView recyclerView;

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
        return new CursorLoader(getActivity(), RouteProvider.Routes.CONTENT_URI,
                new String[]{RouteColumns.TAG, RouteColumns.TITLE},
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, final Cursor data) {
        Log.d("Luke", "LukeonLoadFinished: "+data.getCount());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<RouteListViewHolder>() {
            @Override
            public RouteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item, parent, false);
                return new RouteListViewHolder(v);
            }

            @Override
            public void onBindViewHolder(RouteListViewHolder holder, int position) {
                if(data.moveToPosition(position)){
                    final String tag = data.getString(data.getColumnIndex(RouteColumns.TAG));
                    holder.mTitle.setText(data.getString(data.getColumnIndex(RouteColumns.TITLE)));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bndlanimation =
                                    ActivityOptions.makeCustomAnimation(getActivity(), R.anim.animation,R.anim.animation2).toBundle();
                            startActivity(new Intent(getActivity(), RouteDetailActvity.class).putExtra(Extras.DATA, tag), bndlanimation);
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
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }

    private class RouteListViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;

        public RouteListViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.route_list_item_title);
        }
    }

}
