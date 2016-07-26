package com.sam_chordas.android.stockhawk.adapter;

/**
 * Created by heleneshaikh on 26/07/16.
 */
public class SparkAdapter extends com.robinhood.spark.SparkAdapter {

    private float[] yData;

    public SparkAdapter(float[] yData) {
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

    /*
    public void swapData(float []data){
        yData=data;
        notifyDataSetChanged();
    }
     */
}
