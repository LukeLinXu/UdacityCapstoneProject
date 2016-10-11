package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "direction")
public class Direction {

    @Attribute(name = "tag", required = false)
    private String tag;

    @Attribute(name = "title", required = false)
    private String title;

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "useForUI", required = false)
    private boolean useForUI;

    @Attribute(name = "branch", required = false)
    private String branch;

    @ElementList(inline = true, required = false)
    private List<Stop> stopList;

    @ElementList(inline = true, required = false)
    private List<Prediction> predictionList;

}
