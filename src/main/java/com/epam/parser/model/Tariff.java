package com.epam.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tariff")

public class Tariff extends AbstractTariff {

    @XmlElement(name = "favorite-number", namespace = "http://www.example.com/tariffs", required = true)
    private String favoriteNumber;

    public Tariff() {

        super();

    }

    public Tariff(String name, Price price, String favoriteNNumber) {

        super(name, price);

        this.favoriteNumber = favoriteNNumber;

    }

    public String getFavoriteNumber() {

        return favoriteNumber;
    }

    public void setFavoriteNumber(String favoriteNumber) {

        this.favoriteNumber = favoriteNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((favoriteNumber == null) ? 0 : favoriteNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tariff other = (Tariff) obj;
        if (favoriteNumber == null) {
            if (other.favoriteNumber != null) {
                return false;
            }
        } else if (!favoriteNumber.equals(other.favoriteNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return super.toString() + " [favoriteNumber=" + favoriteNumber + "]";
    }

}