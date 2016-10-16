package com.example.lukelin.udacitycapstoneproject;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;

import com.example.lukelin.udacitycapstoneproject.data.FavoriteColumns;
import com.example.lukelin.udacitycapstoneproject.data.FavoriteProvider;
import com.example.lukelin.udacitycapstoneproject.pojos.Predictions;
import com.example.lukelin.udacitycapstoneproject.pojos.PredictionsResult;
import com.example.lukelin.udacitycapstoneproject.util.RestClient;
import com.example.lukelin.udacitycapstoneproject.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by LukeLin on 2016-10-15.
 */

public class FavoriteIntentService extends IntentService {


    public FavoriteIntentService() {
        super("FavoriteIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Cursor initQueryCursor = getContentResolver().query(FavoriteProvider.Favorites.CONTENT_URI,
                new String[] { "Distinct " + FavoriteColumns.TAG}, null,
                null, null);
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
        if(initQueryCursor.getCount() == 0 || initQueryCursor == null){
            try {
                Response<PredictionsResult> response = RestClient.service.getPredictionsForMultiStops("ttc", new String[]{"199|5464", "39|5950"}).execute();
                List<Predictions> predictionsList = response.body().getPredictionsList();
                long timestamp = System.currentTimeMillis();
                for(Predictions predictions : predictionsList){
                    batchOperations.add(Utils.buildBatchOperation(predictions.getFavorite(timestamp)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            String[] tags = new String[initQueryCursor.getCount()];
            int i = 0;
            while (initQueryCursor.moveToNext()){
                tags[i] = initQueryCursor.getString(initQueryCursor.getColumnIndex(FavoriteColumns.TAG));
                i++;
            }
            try {
                Response<PredictionsResult> response = RestClient.service.getPredictionsForMultiStops("ttc", tags).execute();
                List<Predictions> predictionsList = response.body().getPredictionsList();
                long timestamp = System.currentTimeMillis();
                for(Predictions predictions : predictionsList){
                    batchOperations.add(Utils.buildBatchOperationUpdate(predictions.getFavorite(timestamp)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            getContentResolver().applyBatch(FavoriteProvider.AUTHORITY,
                    batchOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        ResultReceiver rec = intent.getParcelableExtra("receiverTag");
        Bundle b=new Bundle();
        rec.send(101, b);
    }
}
