package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "body")
public class RouteList {

    @Attribute(name = "copyright")
    private String copyright;

    @ElementList(inline = true)
    private List<Route> routeList;

}
