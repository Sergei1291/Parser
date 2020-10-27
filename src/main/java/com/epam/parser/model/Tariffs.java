package com.epam.parser.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tariffs", namespace = "http://www.example.com/tariffs")

public class Tariffs {

    @XmlElements({
            @XmlElement(name = "tariff", namespace = "http://www.example.com/tariffs", type = Tariff.class),
            @XmlElement(name = "payroll-tariff", namespace = "http://www.example.com/tariffs", type = PayrollTariff.class)
    })
    private List<AbstractTariff> abstractTariffs = new ArrayList<>();

    public Tariffs() {
    }

    public List<AbstractTariff> getAbstractTariffs() {

        return this.abstractTariffs;
    }

    @Override
    public int hashCode() {

        return abstractTariffs == null ? 0 : abstractTariffs.hashCode();
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
        Tariffs other = (Tariffs) obj;
        if (abstractTariffs == null) {
            if (other.abstractTariffs != null) {
                return false;
            }
        } else if (!abstractTariffs.equals(other.abstractTariffs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + "[abstractTariff=" + abstractTariffs + "]";
    }

}