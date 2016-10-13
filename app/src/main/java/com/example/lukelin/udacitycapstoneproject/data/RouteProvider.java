package com.example.lukelin.udacitycapstoneproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = RouteProvider.AUTHORITY, database = RouteDatabase.class)
public class RouteProvider {
    public static final String AUTHORITY = "com.example.lukelin.udacitycapstoneproject.data.RouteProvider";

    @TableEndpoint(table = RouteDatabase.ROUTES)
    public static class Routes {
        @ContentUri(
            path = "routes",
            type = "vnd.android.cursor.dir/route")

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/routes");

    }
}
