package com.example.lukelin.udacitycapstoneproject.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public class StopColumns {

    @DataType(DataType.Type.TEXT) @PrimaryKey @NotNull
    public static final String TAG = "tag";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TITLE = "title";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String LAT = "lat";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String LON = "lon";

}
