package com.epam.parser.logic.sax;

import com.epam.parser.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TariffHandler extends DefaultHandler {

    private final static String TAG_TARIFF = "tariff";
    private final static String TAG_PAYROLL_TARIFF = "payroll-tariff";
    private final static String TAG_PRICE = "price";

    private final static String ATTRIBUTE_INCLUDED_TRAFFIC = "included-traffic";

    private final static String TAG_CALL = "call";
    private final static String TAG_TRAFFIC = "traffic";
    private final static String TAG_SMS = "sms";
    private final static String TAG_FAVORITE_NUMBER = "favorite-number";
    private final static String TAG_PAYROLL = "payroll";
    private final static List<String> TAG_TYPES = Arrays.asList(TAG_CALL, TAG_TRAFFIC, TAG_SMS, TAG_FAVORITE_NUMBER, TAG_PAYROLL);

    private List<AbstractTariff> abstractTariffs = new ArrayList<>();
    private AbstractTariff currentAbstractTariff;
    private Price currentPrice;
    private String currentTagType;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        switch (localName) {
            case TAG_TARIFF:
                currentAbstractTariff = new Tariff();
                setAttributes(attrs);
                break;
            case TAG_PAYROLL_TARIFF:
                currentAbstractTariff = new PayrollTariff();
                setAttributes(attrs);
                break;
            case TAG_PRICE:
                currentPrice = new Price();
                break;
            default:
                findTagSimpleType(localName);
        }

    }

    private void setAttributes(Attributes attributes) {

        if ((currentAbstractTariff instanceof PayrollTariff) && (attributes.getLength() == 1)) {
            PayrollTariff payrollTariff = (PayrollTariff) currentAbstractTariff;
            IncludedTraffic includedTraffic = IncludedTraffic.ONE;
            payrollTariff.setIncludedTraffic(includedTraffic);
        }

        for (int i = 0; i < attributes.getLength(); i++) {

            String attributeName = attributes.getLocalName(i);
            String attributeValue = attributes.getValue(i);

            if (ATTRIBUTE_INCLUDED_TRAFFIC.equals((attributeName))) {
                String includedTrafficName = attributeValue.toUpperCase();
                IncludedTraffic includedTraffic = IncludedTraffic.valueOf(includedTrafficName);
                PayrollTariff payrollTariff = (PayrollTariff) currentAbstractTariff;
                payrollTariff.setIncludedTraffic(includedTraffic);
                continue;
            }

            currentAbstractTariff.setName(attributeValue);

        }

    }

    private void findTagSimpleType(String localName) {

        currentTagType = null;

        for (String tagType : TAG_TYPES) {

            if (tagType.equals(localName)) {
                currentTagType = tagType;
            }

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (currentTagType == null) {

            return;
        }

        String content = new String(ch, start, length);

        switch (currentTagType) {
            case TAG_FAVORITE_NUMBER:
                Tariff tariff = (Tariff) currentAbstractTariff;
                tariff.setFavoriteNumber(content);
                break;
            case TAG_PAYROLL:
                int payroll = Integer.parseInt(content);
                PayrollTariff payrollTariff = (PayrollTariff) currentAbstractTariff;
                payrollTariff.setPayroll(payroll);
                break;
            case TAG_CALL:
                int call = Integer.parseInt(content);
                currentPrice.setCall(call);
                break;
            case TAG_TRAFFIC:
                int traffic = Integer.parseInt(content);
                currentPrice.setTraffic(traffic);
                break;
            case TAG_SMS:
                int sms = Integer.parseInt(content);
                currentPrice.setSms(sms);
        }

        currentTagType = null;

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (TAG_PRICE.equals(localName)) {
            currentAbstractTariff.setPrice(currentPrice);
        }

        if ((TAG_TARIFF.equals(localName)) || (TAG_PAYROLL_TARIFF.equals(localName))) {
            abstractTariffs.add(currentAbstractTariff);
        }

    }

    public List<AbstractTariff> getAbstractTariffs() {

        return abstractTariffs;
    }

    public int getSize() {

        return abstractTariffs.size();
    }

}