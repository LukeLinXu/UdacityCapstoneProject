package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "path")
public class Path {

    @ElementList(inline = true)
    private List<Point> pointList;

}
