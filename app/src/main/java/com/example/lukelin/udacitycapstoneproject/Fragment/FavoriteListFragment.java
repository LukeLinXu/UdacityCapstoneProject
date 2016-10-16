package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
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

import com.example.lukelin.udacitycapstoneproject.FavoriteIntentService;
import com.example.lukelin.udacitycapstoneproject.MyResultReceiver;
import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.RecyclerViewComponent.PredictionsAdapter;
import com.example.lukelin.udacitycapstoneproject.data.FavoriteColumns;
import com.example.lukelin.udacitycapstoneproject.data.FavoriteProvider;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lukelin on 2016-10-14.
 */

public class FavoriteListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, MyResultReceiver.Receiver{
    private static final int CURSOR_LOADER_ID = 0;
    private RecyclerView recyclerView;
    private MyResultReceiver mReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        mReceiver = new MyResultReceiver(new Handler());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerview);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mReceiver.setReceiver(this);
        getActivity().startService(new Intent(getActivity(), FavoriteIntentService.class).putExtra("receiverTag", mReceiver));
    }

    @Override
    public void onPause() {
        super.onPause();
        mReceiver.setReceiver(null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), FavoriteProvider.Favorites.CONTENT_URI,
                new String[]{FavoriteColumns.TAG,
                        FavoriteColumns.TIMESTAMP,
                        FavoriteColumns.ROUTE_TITLE,
                        FavoriteColumns.STOP_TITLE,
                        FavoriteColumns.CONTENT},
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        Log.d("Luke", "LukeonLoadFinished: "+data.getCount());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Predictions> object = new ArrayList<>();
        while (data.moveToNext()){
            Gson gson = new Gson();
            Predictions predictions = gson.fromJson(data.getString(data.getColumnIndex(FavoriteColumns.CONTENT)), Predictions.class);
            object.add(predictions);
        }
        PredictionsAdapter predictionsAdapter = new PredictionsAdapter(object);
        recyclerView.setAdapter(predictionsAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Log.d(TAG, "onReceiveResult: "+resultCode);
    }

}
