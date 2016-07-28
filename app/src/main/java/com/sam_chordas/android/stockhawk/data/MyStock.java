package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class MyStock {
    private Query query;

    public Query getQuery ()
    {
        return query;
    }

    public void setQuery (Query query)
    {
        this.query = query;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [query = "+query+"]";
    }
}
