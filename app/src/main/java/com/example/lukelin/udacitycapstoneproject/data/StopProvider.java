package com.example.lukelin.udacitycapstoneproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = StopProvider.AUTHORITY, database = StopDatabase.class)
public class StopProvider {
    public static final String AUTHORITY = "com.example.lukelin.udacitycapstoneproject.data.StopProvider";

    @TableEndpoint(table = StopDatabase.STOPS)
    public static class Stops {
        @ContentUri(
            path = "stops",
            type = "vnd.android.cursor.dir/stop",
            defaultSort = StopColumns.TAG + " ASC")

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/stops");

    }
}
