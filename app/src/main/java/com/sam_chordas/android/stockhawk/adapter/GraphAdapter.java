package com.sam_chordas.android.stockhawk.adapter;

import com.robinhood.spark.SparkAdapter;

/**
 * Created by heleneshaikh on 01/08/16.
 */

public class GraphAdapter extends SparkAdapter {
    private float[] yData;

    public GraphAdapter(float[] yData) {
        this.yData = yData;
    }

    @Override
    public int getCount() {
        return yData.length;
    }

    @Override
    public Object getItem(int index) {
        return yData[index];
    }

    @Override
    public float getY(int index) {
        return yData[index];
    }

    public void swapData(float[] data) {
        yData = data;
        notifyDataSetChanged();
    }
}
