package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.os.Bundle;

import com.sam_chordas.android.stockhawk.R;

public class DetailActivity extends Activity {
    public final static String STOCK_SYMBOL = "stock_symbol";
    public final static String ENDPOINT = "https://query.yahooapis.com/v1/public/yql?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

    }
}
