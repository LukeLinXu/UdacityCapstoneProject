package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by lukelin on 2016-10-07.
 */
@Root(name = "body")
public class VehicleLocationResult {

    @Attribute(name = "copyright")
    private String copyright;

    @ElementList(inline = true)
    private List<Vehicle> vehicleList;

    @Element(name = "lastTime")
    private LastTime lastTime;

}
