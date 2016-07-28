package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Results {
    private Quote[] quote;

    public Quote[] getQuote ()
    {
        return quote;
    }

    public void setQuote (Quote[] quote)
    {
        this.quote = quote;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quote = "+quote+"]";
    }
}
