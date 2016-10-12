package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = RouteDatabase.VERSION)
public class RouteDatabase {
    private RouteDatabase(){}

    public static final int VERSION = 1;

    @Table(RouteColumns.class) public static final String ROUTES = "routes";
}
