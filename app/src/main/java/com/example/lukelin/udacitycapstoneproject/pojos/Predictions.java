package com.example.lukelin.udacitycapstoneproject.pojos;

import com.google.gson.Gson;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "predictions")
public class Predictions {

    @Attribute(name = "agencyTitle")
    private String agencyTitle;

    @Attribute(name = "routeTitle", required = false)
    private String routeTitle;

    @Attribute(name = "routeTag", required = false)
    private String routeTag;

    @Attribute(name = "stopTitle", required = false)
    private String stopTitle;

    @Attribute(name = "stopTag", required = false)
    private String stopTag;

    @Attribute(name = "dirTitleBecauseNoPredictions", required = false)
    private String dirTitleBecauseNoPredictions;

    @ElementList(inline = true, required = false)
    private List<Direction> directionList;

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    public List<Direction> getDirectionList() {
        if (directionList == null) return new ArrayList<>();
        for(Direction direction : directionList){
            direction.setRouteTag(routeTag);
        }
        return directionList;
    }

    public Favorite getFavorite(long timestamp){
        Gson gson = new Gson();
        String content = gson.toJson(getDirectionList());
        return new Favorite(routeTag+"|"+stopTag, routeTitle, stopTitle, String.valueOf(timestamp), content);
    }
}
