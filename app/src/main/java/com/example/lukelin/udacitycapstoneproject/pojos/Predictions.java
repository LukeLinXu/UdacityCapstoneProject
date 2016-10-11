package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

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
    private boolean stopTitle;

    @Attribute(name = "stopTag", required = false)
    private String stopTag;

    @Attribute(name = "dirTitleBecauseNoPredictions", required = false)
    private String dirTitleBecauseNoPredictions;

    @ElementList(inline = true, required = false)
    private List<Direction> directionList;

    @Element(name = "message", required = false)
    private Message message;

}
