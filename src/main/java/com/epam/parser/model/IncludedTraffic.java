package com.epam.parser.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "included-traffic")

public enum IncludedTraffic {

    @XmlEnumValue("one")
    ONE,
    @XmlEnumValue("two")
    TWO,
    @XmlEnumValue("four")
    FOUR,
    @XmlEnumValue("eight")
    EIGHT,
    @XmlEnumValue("sixteen")
    SIXTEEN

}