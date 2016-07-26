package com.sam_chordas.android.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.rest.Utils;

/**
 * Created by heleneshaikh on 26/07/16.
 */
public class StockWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) { //factory acts as an adapter
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {}

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }

                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                        new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                                QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                        QuoteColumns.ISCURRENT + " = ?",
                        new String[]{"1"},
                        null);
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_list);

                if (data.moveToPosition(position)) {
                    String symbol = data.getString(data.getColumnIndex(QuoteColumns.SYMBOL));
                    String bid = data.getString(data.getColumnIndex(QuoteColumns.BIDPRICE));
                    String change = data.getString(data.getColumnIndex(QuoteColumns.CHANGE));
                    String percentChange = data.getString(data.getColumnIndex(QuoteColumns.PERCENT_CHANGE));
                    views.setTextViewText(R.id.symbol, symbol);
                    views.setTextViewText(R.id.bid, bid);

                    if (Utils.showPercent) {
                        views.setTextViewText(R.id.change, percentChange);
                    } else {
                        views.setTextViewText(R.id.change, change);
                    }

                    if (data.getInt(data.getColumnIndex(QuoteColumns.ISUP)) == 1) {
                        views.setInt(R.id.change, "setBackgroundResource",
                                android.graphics.Color.BLACK);
                    } else {
                        views.setInt(R.id.change, "setBackgroundResource",
                                R.drawable.percent_change_pill_red);
                    }

                    final Intent intent = new Intent();
                    intent.setAction(StockWidgetProvider.DETAIL_ACTION);
                    intent.putExtra(QuoteColumns.SYMBOL, data.getString(data.getColumnIndex(QuoteColumns.SYMBOL)));
                    intent.putExtra(QuoteColumns.BIDPRICE, data.getString(data.getColumnIndex(QuoteColumns.BIDPRICE)));
                    views.setOnClickFillInIntent(R.id.widget_list_item, intent);
                }
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_list);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(data.getColumnIndex(QuoteColumns._ID));
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
