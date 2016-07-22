package com.sam_chordas.android.stockhawk.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by sam_chordas on 10/5/15.
 */
@ContentProvider(authority = QuoteProvider.AUTHORITY, database = QuoteDatabase.class)

public class QuoteProvider {
    public static final String AUTHORITY = "com.sam_chordas.android.stockhawk.data.QuoteProvider";
    private static final String QUOTE = "quotes";

    @TableEndpoint(table = QuoteDatabase.QUOTES) //table containing uri's
    public static class Quotes {
        @ContentUri( //uri doesn't change
                path = QUOTE,
                type = "vnd.android.cursor.dir/quote" //type that is returned for a given URI by the ContentProvider
        )
        public static final Uri CONTENT_URI = buildUri(QUOTE);

        @InexactContentUri( //dynamic uri
                name = "QUOTE_ID",
                path = QUOTE + "/*",
                type = "vnd.android.cursor.item/quote",
                whereColumn = QuoteColumns.SYMBOL,
                pathSegment = 1
        )
        public static Uri withSymbol(String symbol) {
            return buildUri(QUOTE, symbol);
        }
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = Uri.parse("content://" + AUTHORITY).buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }


}
