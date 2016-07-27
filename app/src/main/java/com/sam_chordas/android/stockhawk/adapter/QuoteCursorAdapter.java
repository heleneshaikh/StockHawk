package com.sam_chordas.android.stockhawk.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.rest.Utils;
import com.sam_chordas.android.stockhawk.touch_helper.ItemTouchHelperAdapter;
import com.sam_chordas.android.stockhawk.touch_helper.ItemTouchHelperViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

//SHOW DATA IN LIST_ITEM_QUOTE
public class QuoteCursorAdapter extends CursorRecyclerViewAdapter<QuoteCursorAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private static Context context;
    private static Typeface robotoLight;
    private static Typeface lalezar;

    public QuoteCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        QuoteCursorAdapter.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements ItemTouchHelperViewHolder, View.OnClickListener {
        @BindView(R.id.stock_symbol)
        TextView symbol;
        @BindView(R.id.bid_price)
        TextView bidPrice;
        @BindView(R.id.change)
        TextView change;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            symbol.setTypeface(robotoLight);
            bidPrice.setTypeface(lalezar);
            change.setTypeface(lalezar);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }

        @Override
        public void onClick(View v) {}
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        robotoLight = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        lalezar = Typeface.createFromAsset(context.getAssets(), "fonts/Lalezar-Regular.ttf");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_quote, parent, false); //instead of card view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final Cursor cursor) {
        int indexSymbol = cursor.getColumnIndex("symbol"); //1
        int bidPriceIndex = cursor.getColumnIndex("bid_price"); //2
        int percentChangeIndex = cursor.getColumnIndex("percent_change"); //3
        int changeIndex = cursor.getColumnIndex("change"); //4

        viewHolder.symbol.setText(cursor.getString(indexSymbol));  //YHOO, AAPL, GOOG, MSFT
        viewHolder.bidPrice.setText(cursor.getString(bidPriceIndex)); //38.25, 99.26, 732.53

        if (cursor.getInt(cursor.getColumnIndex("is_up")) == 1) {
                viewHolder.change.setBackgroundResource(
                       R.drawable.percent_change_pill_green);
        } else {
                viewHolder.change.setBackgroundResource (R.drawable.percent_change_pill_red);
        }
        if (Utils.showPercent) {
            viewHolder.change.setText(cursor.getString(percentChangeIndex));
        } else {
            viewHolder.change.setText(cursor.getString(changeIndex));
        }
    }

    @Override
    public void onItemDismiss(int position) {
        Cursor c = getCursor();
        c.moveToPosition(position);
        String symbol = c.getString(c.getColumnIndex(QuoteColumns.SYMBOL));
        context.getContentResolver().delete(QuoteProvider.Quotes.withSymbol(symbol), null, null);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
