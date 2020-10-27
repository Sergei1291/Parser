package com.epam.parser.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payroll-tariff")

public class PayrollTariff extends AbstractTariff {

    @XmlElement(name = "payroll", namespace = "http://www.example.com/tariffs", required = true)
    private int payroll;
    @XmlAttribute(name = "included-traffic", required = false)
    private IncludedTraffic includedTraffic;

    public PayrollTariff() {

        super();

    }

    public PayrollTariff(String name, Price price, int payroll, IncludedTraffic includedTraffic) {

        super(name, price);

        this.payroll = payroll;
        this.includedTraffic = includedTraffic;

    }

    public int getPayroll() {

        return payroll;
    }

    public void setPayroll(int payroll) {

        this.payroll = payroll;
    }

    public IncludedTraffic getIncludedTraffic() {

        return includedTraffic;
    }

    public void setIncludedTraffic(IncludedTraffic includedTraffic) {

        this.includedTraffic = includedTraffic;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((includedTraffic == null) ? 0 : includedTraffic.hashCode());
        result = prime * result + payroll;
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
        PayrollTariff other = (PayrollTariff) obj;
        if (includedTraffic != other.includedTraffic) {
            return false;
        }
        if (payroll != other.payroll) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return super.toString() + " [payroll=" + payroll + ", includedTraffic=" + includedTraffic + "]";
    }

}