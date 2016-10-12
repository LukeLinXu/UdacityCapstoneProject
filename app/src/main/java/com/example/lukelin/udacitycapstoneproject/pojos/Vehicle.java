package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "vehicle")
public class Vehicle {

    @Attribute(name = "id")
    private String id;
    @Attribute(name = "routeTag")
    private String routeTag;
    @Attribute(name = "dirTag", required = false)
    private String dirTag;
    @Attribute(name = "lat", required = false)
    private double lat;
    @Attribute(name = "lon", required = false)
    private double lon;
    @Attribute(name = "secsSinceReport", required = false)
    private int secsSinceReport;
    @Attribute(name = "predictable", required = false)
    private boolean predictable;
    @Attribute(name = "heading", required = false)
    private int heading;

}
