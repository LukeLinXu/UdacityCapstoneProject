package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "point")
public class Point {

    @Attribute(name = "lat")
    private double lat;
    @Attribute(name = "lon")
    private double lon;

}
