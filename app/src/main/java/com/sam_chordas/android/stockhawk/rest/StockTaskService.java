package com.sam_chordas.android.stockhawk.rest;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by sam_chordas on 9/30/15.
 * The GCMTask service is primarily for periodic tasks. However, OnRunTask can be called directly
 * and is used for the initialization and adding task as well.
 */

//FETCH DATA FROM YAHOO API VIA OKHTTP

public class StockTaskService extends GcmTaskService { //to schedule tasks
    private String LOG_TAG = StockTaskService.class.getSimpleName();
    private OkHttpClient client = new OkHttpClient();
    private Context context;
    private StringBuilder storedSymbols = new StringBuilder();
    private boolean isUpdate;
    private final static String ENDPOINT = "https://query.yahooapis.com/v1/public/yql?q=";
    public static final String ACTION_DATA_UPDATED = "com.sam_chordas.android.stockhawk.ACTION_DATA_UPDATED";

    public StockTaskService() {}

    public StockTaskService(Context context) {
        this.context = context;
    }

    String fetchData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute(); //make the call
        return response.body().string();
    }

    @Override
    public int onRunTask(TaskParams params) { //parameters handed off to client app
        String tag = params.getTag(); //"init"
        Cursor initQueryCursor;
        if (context == null) {
            context = this;
        }
        StringBuilder urlStringBuilder = new StringBuilder();
        try {
            // Base URL for the Yahoo query
            urlStringBuilder.append(ENDPOINT);
            urlStringBuilder.append(URLEncoder.encode("select * from yahoo.finance.quotes where symbol in (", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //YHOO, AAPL, GOOG, MSFT added as init data
        if (params.getTag().equals("init") || params.getTag().equals("periodic")) {
            isUpdate = true;
            initQueryCursor = context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{"Distinct " + QuoteColumns.SYMBOL}, //see if there's already something in DB
                    null, null, null);
            if (initQueryCursor.getCount() == 0 || initQueryCursor == null) {
                // Init task. Populates DB with quotes for the symbols seen below
                try {
                    urlStringBuilder.append( //append to ENDPOINT
                            URLEncoder.encode("\"YHOO\",\"AAPL\",\"GOOG\",\"MSFT\")", "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (initQueryCursor != null) {
                DatabaseUtils.dumpCursor(initQueryCursor); //print to stream
                initQueryCursor.moveToFirst();
                for (int i = 0; i < initQueryCursor.getCount(); i++) {
                    storedSymbols.append("\"" +
                            initQueryCursor.getString(initQueryCursor.getColumnIndex("symbol")) + "\",");
                    initQueryCursor.moveToNext();
                }
                storedSymbols.replace(storedSymbols.length() - 1, storedSymbols.length(), ")");
                try {
                    urlStringBuilder.append(URLEncoder.encode(storedSymbols.toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else if (params.getTag().equals("add")) {
            isUpdate = false;
            String stockInput = params.getExtras().getString("symbol");
            try {
                urlStringBuilder.append(URLEncoder.encode("\"" + stockInput + "\")", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // finalize the URL for the API query.
        urlStringBuilder.append("&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables."
                + "org%2Falltableswithkeys&callback=");

        String urlString;
        String response;
        int result = GcmNetworkManager.RESULT_FAILURE;
        updateWidget();

        if (urlStringBuilder != null) {
            urlString = urlStringBuilder.toString();
            try {
                response = fetchData(urlString);
                result = GcmNetworkManager.RESULT_SUCCESS;
                try {
                    ContentValues contentValues = new ContentValues();
                    if (isUpdate) {
                        contentValues.put(QuoteColumns.ISCURRENT, 0);
                        context.getContentResolver().update(QuoteProvider.Quotes.CONTENT_URI, contentValues,
                                null, null);
                    }
                    ArrayList<ContentProviderOperation> arrayList = Utils.quoteJsonToContentVals(response);
                    if (arrayList == null) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(context, R.string.no_exist, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        context.getContentResolver().applyBatch(QuoteProvider.AUTHORITY,
                                arrayList);
                    }
                } catch (RemoteException | OperationApplicationException e) {
                    Log.e(LOG_TAG, "Error applying batch insert", e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void updateWidget() {
        Context mContext = context;
        // Setting the package ensures that only components in our app will receive the broadcast
        Intent dataUpdatedIntent = new Intent(ACTION_DATA_UPDATED)
                .setPackage(mContext.getPackageName());
        mContext.sendBroadcast(dataUpdatedIntent);
    }

}
