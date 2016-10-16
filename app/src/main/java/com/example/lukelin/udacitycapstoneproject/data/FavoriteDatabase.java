package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = FavoriteDatabase.VERSION)
public class FavoriteDatabase {
    private FavoriteDatabase(){}

    public static final int VERSION = 1;

    @Table(FavoriteColumns.class) public static final String FAVORITES = "favorites";
}
