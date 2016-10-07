package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "body")
public class GetRouteResult {

    @Attribute(name = "copyright")
    private String copyright;

    @Element(name = "route")
    private Route route;
}
