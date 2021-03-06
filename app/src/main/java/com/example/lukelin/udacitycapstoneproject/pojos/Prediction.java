package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "prediction")
public class Prediction {

    @Attribute(name = "epochTime")
    private long epochTime;
    @Attribute(name = "seconds")
    private int seconds;
    @Attribute(name = "minutes")
    private int minutes;
    @Attribute(name = "isDeparture", required = false)
    private boolean isDeparture;
    @Attribute(name = "affectedByLayover", required = false)
    private boolean affectedByLayover;
    @Attribute(name = "branch", required = false)
    private String branch;
    @Attribute(name = "dirTag", required = false)
    private String dirTag;
    @Attribute(name = "vehicle", required = false)
    private String vehicle;
    @Attribute(name = "block", required = false)
    private String block;
    @Attribute(name = "tripTag", required = false)
    private String tripTag;

    private String routeTag;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEpochTime() {
        return epochTime;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getBranch() {
        return branch;
    }

    public String getDirTag() {
        return dirTag;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }
}
