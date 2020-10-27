package com.epam.parser.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstract-tariff")

public class AbstractTariff {

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String name;
    @XmlElement(name = "price", namespace = "http://www.example.com/tariffs", required = true)
    private Price price;

    public AbstractTariff() {
    }

    public AbstractTariff(String name, Price price) {

        this.name = name;
        this.price = price;

    }

    public String getName() {

        return name;
    }
    
    public void setName(String name) {

        this.name = name;
    }

    public Price getPrice() {

        return price;
    }

    public void setPrice(Price price) {

        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        AbstractTariff other = (AbstractTariff) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (price == null) {
            if (other.price != null) {
                return false;
            }
        } else if (!price.equals(other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + " [name=" + name + ", price=" + price + "]";
    }

}