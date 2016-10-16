package com.example.lukelin.udacitycapstoneproject.pojos;

/**
 * Created by LukeLin on 2016-10-15.
 */

public class Favorite {

    private String tag;
    private String routeTitle;
    private String stopTitle;
    private String timestamp;
    private String content;

    public Favorite(String tag, String routeTitle, String stopTitle, String timestamp, String content) {
        this.tag = tag;
        this.routeTitle = routeTitle;
        this.stopTitle = stopTitle;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public String getStopTitle() {
        return stopTitle;
    }
}
