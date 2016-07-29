package com.sam_chordas.android.stockhawk.data;

import java.util.ArrayList;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Results {
    private ArrayList<Quote> quote;

    public ArrayList<Quote> getQuote() {
        return quote;
    }

    public void setQuote(ArrayList<Quote> quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "ClassPojo [quote = " + quote + "]";
    }
}
