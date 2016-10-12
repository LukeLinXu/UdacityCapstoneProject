package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public class RouteColumns {

    @DataType(DataType.Type.TEXT) @PrimaryKey @NotNull
    public static final String TAG = "tag";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TITLE = "title";

}
