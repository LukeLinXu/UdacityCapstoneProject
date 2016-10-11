package com.example.lukelin.udacitycapstoneproject.pojos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by lukelin on 2016-10-07.
 */

@Root(name = "message")
public class Message {

    @Attribute(name = "text")
    private String text;
    @Attribute(name = "priority")
    private String priority;

}
