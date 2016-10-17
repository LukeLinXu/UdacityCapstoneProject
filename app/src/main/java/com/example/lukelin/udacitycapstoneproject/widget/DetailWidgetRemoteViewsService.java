package com.example.lukelin.udacitycapstoneproject.widget;

import android.content.Intent;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.data.FavoriteColumns;
import com.example.lukelin.udacitycapstoneproject.data.FavoriteProvider;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.google.gson.Gson;


public class DetailWidgetRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }
                data = getContentResolver().query(FavoriteProvider.Favorites.CONTENT_URI,
                        new String[] {FavoriteColumns.TAG,
                        FavoriteColumns.STOP_TITLE,
                        FavoriteColumns.ROUTE_TITLE,
                        FavoriteColumns.CONTENT},
                        null,
                        null,
                        null);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_detail_list_item);

                String routeTag = data.getString(data.getColumnIndex(FavoriteColumns.TAG));
                String stopTitle = data.getString(data.getColumnIndex(FavoriteColumns.STOP_TITLE));
                String routeTitle = data.getString(data.getColumnIndex(FavoriteColumns.ROUTE_TITLE));

                Gson gson = new Gson();
                Predictions predictions = gson.fromJson(data.getString(data.getColumnIndex(FavoriteColumns.CONTENT)), Predictions.class);

                views.setTextViewText(R.id.widget_route_tag, routeTag.split("\\|")[0]);
                views.setTextViewText(R.id.widget_route_title, routeTitle);
                views.setTextViewText(R.id.widget_stop_title, stopTitle);
                views.setTextViewText(R.id.widget_time_left, predictions.getFirstTimeLeft());

                Intent fillInIntent = new Intent();
                views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_detail_list_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getString(data.getColumnIndex(FavoriteColumns.TAG)).hashCode();
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
