package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "route")
public class Route {
    @Attribute(name = "tag")
    private String tag;
    @Attribute(name = "title")
    private String title;
    @Attribute(name = "shortTitle", required = false)
    private String shortTitle;
    @Attribute(name = "color", required = false)
    private String color;
    @Attribute(name = "oppositeColor", required = false)
    private String oppositeColor;
    @Attribute(name = "latMin", required = false)
    private double latMin;
    @Attribute(name = "latMax", required = false)
    private double latMax;
    @Attribute(name = "lonMin", required = false)
    private double lonMin;
    @Attribute(name = "lonMax", required = false)
    private double lonMax;
    @ElementList(inline = true, required = false)
    private List<Stop> stopList;
    @ElementList(inline = true, required = false)
    private List<Direction> directionList;
    @ElementList(inline = true, required = false)
    private List<Path> pathList;


    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public List<Stop> getStopList() {
        return stopList;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }
}
