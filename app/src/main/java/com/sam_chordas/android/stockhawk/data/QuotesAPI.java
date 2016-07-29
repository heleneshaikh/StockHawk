package com.sam_chordas.android.stockhawk.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public interface QuotesAPI {

    @GET("v1/public/yql?&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=")
    Call<MyStock> getFeed(@Query("q") String query);


}
