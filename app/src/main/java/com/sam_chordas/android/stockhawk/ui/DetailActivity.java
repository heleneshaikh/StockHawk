package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.robinhood.spark.SparkView;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.adapter.GraphAdapter;
import com.sam_chordas.android.stockhawk.data.MyStock;
import com.sam_chordas.android.stockhawk.data.Quote;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuotesAPI;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends Activity {
    public final static String STOCK_SYMBOL = "stock_symbol";
    public final static String ENDPOINT = "https://query.yahooapis.com/";
    static List<Quote> quoteList;
    @BindView(R.id.sparkView)
    SparkView sparkView;
    @BindView(R.id.symbol_value)
    TextView symbolView;
    @BindView(R.id.min_bid_value)
    TextView minBidView;
    @BindView(R.id.max_bid_value)
    TextView maxBidView;
//    @BindView(R.id.scrub_info_textview) TextView scrubInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        ButterKnife.bind(this);

        //CALENDAR
        Calendar rightNow = Calendar.getInstance();
        String endYear = String.valueOf(rightNow.get(Calendar.YEAR));
        String endMonth = String.valueOf(rightNow.get(Calendar.MONTH) + 1);
        String endDay = String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH));
        String endDate = endYear + "-" + endMonth + "-" + endDay; // 2016/7/28

        rightNow.add(Calendar.MONTH, -2);
        String beginYear = String.valueOf(rightNow.get(Calendar.YEAR));
        String beginMonth = String.valueOf(rightNow.get(Calendar.MONTH) + 1);
        String beginDay = String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH));
        String beginDate = beginYear + "-" + beginMonth + "-" + beginDay;  // 2016/04/28

        // SET UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuotesAPI api = retrofit.create(QuotesAPI.class);
        final String symbol = getIntent().getStringExtra(QuoteColumns.SYMBOL);
        final String query = "select * from yahoo.finance.historicaldata where symbol = \'" + symbol + "\' and startDate = \'" + beginDate + "\' and endDate = \'" + endDate + "\'";

        api.getFeed(query).enqueue(new Callback<MyStock>() {
            @Override
            public void onResponse(Call<MyStock> call, Response<MyStock> response) {
                if (response != null) {
                    Log.v("response", response.raw().toString());
                    MyStock myStock = response.body();
                    quoteList = myStock.getQuery().getResults().getQuote();
                    float data [] = new float[quoteList.size()];
                    for (int i = 0; i < quoteList.size(); i++) {
                        data[i] = Float.parseFloat(quoteList.get(i).getClose());
                    }

                    GraphAdapter adapter = new GraphAdapter(data);
                    sparkView.setAdapter(adapter);

                    //TODO SET THE VIEWS

                    adapter.swapData(data);

                    sparkView.setScrubListener(new SparkView.OnScrubListener(){
                        @Override
                        public void onScrubbed(Object value) {
//                            scrubInfoTextView.setText(getString(R.string.scrub_format, value));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyStock> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG); //why 'this' not working?
                toast.show();
            }
        });
    }
}
