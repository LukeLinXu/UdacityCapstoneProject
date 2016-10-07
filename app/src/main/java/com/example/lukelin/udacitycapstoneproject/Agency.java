package com.example.lukelin.udacitycapstoneproject;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "agency")
public class Agency {

    @Attribute(name = "tag")
    private String tag;
    @Attribute(name = "title")
    private String title;
    @Attribute(name = "regionTitle")
    private String regionTitle;
    @Attribute(name = "shortTitle", required = false)
    private String shortTitle;
}
