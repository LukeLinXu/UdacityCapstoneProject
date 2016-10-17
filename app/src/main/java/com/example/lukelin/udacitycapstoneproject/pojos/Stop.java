package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "stop")
public class Stop {

    @Attribute(name = "tag")
    private String tag;
    @Attribute(name = "title", required = false)
    private String title;
    @Attribute(name = "shortTitle", required = false)
    private String shortTitle;
    @Attribute(name = "lat", required = false)
    private double lat;
    @Attribute(name = "lon", required = false)
    private double lon;
    @Attribute(name = "stopId", required = false)
    private int stopId;

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getStopId() {
        return stopId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }
}
