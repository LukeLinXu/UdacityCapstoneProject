package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = StopDatabase.VERSION)
public class StopDatabase {
    private StopDatabase(){}

    public static final int VERSION = 1;

    @Table(StopColumns.class) public static final String STOPS = "stops";
}
