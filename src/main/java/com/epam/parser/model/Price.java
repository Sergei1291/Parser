package com.epam.parser.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "price")

public class Price {

    @XmlElement(name = "call", namespace = "http://www.example.com/tariffs", required = true)
    private int call;
    @XmlElement(name = "traffic", namespace = "http://www.example.com/tariffs", required = true)
    private int traffic;
    @XmlElement(name = "sms", namespace = "http://www.example.com/tariffs", required = true)
    private int sms;

    public Price() {
    }

    public Price(int call, int traffic, int sms) {

        this.call = call;
        this.traffic = traffic;
        this.sms = sms;

    }

    public int getCall() {

        return call;
    }

    public void setCall(int call) {

        this.call = call;
    }

    public int getTraffic() {

        return traffic;
    }

    public void setTraffic(int traffic) {

        this.traffic = traffic;
    }

    public int getSms() {

        return sms;
    }

    public void setSms(int sms) {

        this.sms = sms;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + call;
        result = prime * result + sms;
        result = prime * result + traffic;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Price other = (Price) obj;
        if (call != other.call) {
            return false;
        }
        if (sms != other.sms) {
            return false;
        }
        if (traffic != other.traffic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + " [call=" + call + ", traffic=" + traffic + ", sms=" + sms + "]";
    }

}