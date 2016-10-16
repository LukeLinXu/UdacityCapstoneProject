package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public class FavoriteColumns {

    @DataType(DataType.Type.TEXT) @PrimaryKey @NotNull
    public static final String TAG = "tag";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TIMESTAMP = "timestamp";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String ROUTE_TITLE = "route_title";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String STOP_TITLE = "stop_title";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String CONTENT = "content";

}
