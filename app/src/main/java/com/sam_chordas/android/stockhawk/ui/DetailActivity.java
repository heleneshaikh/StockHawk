package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.os.Bundle;

import com.sam_chordas.android.stockhawk.R;

public class DetailActivity extends Activity {
    public final static String INTENT_EXTRA_STOCK_SYMBOL = "stock_symbol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }
}
