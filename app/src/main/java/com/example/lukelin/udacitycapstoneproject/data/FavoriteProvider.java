package com.example.lukelin.udacitycapstoneproject.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = FavoriteProvider.AUTHORITY, database = FavoriteDatabase.class)
public class FavoriteProvider {
    public static final String AUTHORITY = "com.example.lukelin.udacitycapstoneproject.data.FavoriteProvider";

    @TableEndpoint(table = FavoriteDatabase.FAVORITES)
    public static class Favorites {
        @ContentUri(
            path = "favorites",
            type = "vnd.android.cursor.dir/favorite")

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites");

    }
}
