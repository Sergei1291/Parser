package com.epam.parser.logic.dom;

import com.epam.parser.model.*;
import com.epam.parser.logic.Parser;
import com.epam.parser.logic.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DomParser implements Parser {

    private final static String TAG_TARIFF = "tariff";
    private final static String TAG_PAYROLL_TARIFF = "payroll-tariff";

    private final static String ATTRIBUTE_NAME = "name";
    private final static String TAG_ELEMENT_CALL = "call";
    private final static String TAG_ELEMENT_TRAFFIC = "traffic";
    private final static String TAG_ELEMENT_SMS = "sms";
    private final static List<String> TAGS_ELEMENT_PRICE = Arrays.asList(TAG_ELEMENT_CALL, TAG_ELEMENT_TRAFFIC, TAG_ELEMENT_SMS);

    private final static String TAG_ELEMENT_FAVORITE_NUMBER = "favorite-number";

    private final static String TAG_ELEMENT_PAYROLL = "payroll";
    private final static String ATTRIBUTE_INCLUDED_TRAFFIC = "included-traffic";

    private AbstractTariff currentAbstractTariff;
    private Price currentPrice;
    private Element currentElement;

    public DomParser() {
    }

    @Override
    public List<AbstractTariff> parse(String fileName) throws ParserException {

        Document document = createDocument(fileName);
        Element rootElement = document.getDocumentElement();
        NodeList abstractTariffElements = rootElement.getChildNodes();

        List<AbstractTariff> abstractTariffs = new ArrayList<>();

        for (int i = 0; i < abstractTariffElements.getLength(); i++) {
            Node currentNode = abstractTariffElements.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                currentElement = (Element) currentNode;
                buildCurrentAbstractTariff();
                abstractTariffs.add(currentAbstractTariff);
            }
        }

        return abstractTariffs;
    }

    private Document createDocument(String fileName) throws ParserException {

        DocumentBuilder documentBuilder = createDocumentBuilder();
        Document document = null;

        try {

            document = documentBuilder.parse(fileName);

        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }

        return document;
    }

    private DocumentBuilder createDocumentBuilder() throws ParserException {

        DocumentBuilder documentBuilder = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException(e);
        }

        return documentBuilder;
    }

    private void buildCurrentAbstractTariff() {

        initializeLinkAbstractTariff();

        initializeAbstractTariffField();

        if (currentAbstractTariff instanceof Tariff) {
            initializeTariffField();
        }

        if (currentAbstractTariff instanceof PayrollTariff) {
            initializePayrollTariffField();
        }

    }

    private void initializeLinkAbstractTariff() {

        String tagName = currentElement.getTagName();

        switch (tagName) {
            case TAG_TARIFF:
                currentAbstractTariff = new Tariff();
                break;
            case TAG_PAYROLL_TARIFF:
                currentAbstractTariff = new PayrollTariff();
                break;
            default:
                throw new IllegalArgumentException("unknown element's tag's name " + tagName);
        }

    }

    private void initializeAbstractTariffField() {

        initializeName();
        initializePrice();

    }

    private void initializeName() {

        String nameContent = currentElement.getAttribute(ATTRIBUTE_NAME);
        currentAbstractTariff.setName(nameContent);

    }

    private void initializePrice() {

        currentPrice = new Price();

        for (String value : TAGS_ELEMENT_PRICE) {
            initializePriceField(value);
        }

        currentAbstractTariff.setPrice(currentPrice);

    }

    private void initializePriceField(String elementName) {

        String content = getElementTextContent(elementName);
        int value = Integer.parseInt(content);

        switch (elementName) {
            case TAG_ELEMENT_CALL:
                currentPrice.setCall(value);
                break;
            case TAG_ELEMENT_TRAFFIC:
                currentPrice.setTraffic(value);
                break;
            case TAG_ELEMENT_SMS:
                currentPrice.setSms(value);
                break;
            default:
                throw new IllegalArgumentException("unknown price's field's name" + elementName);
        }

    }

    private void initializeTariffField() {

        Tariff tariff = (Tariff) currentAbstractTariff;

        initializeFavoriteNumber(tariff);

    }

    private void initializeFavoriteNumber(Tariff tariff) {

        String favoriteNumberContent = getElementTextContent(TAG_ELEMENT_FAVORITE_NUMBER);
        tariff.setFavoriteNumber(favoriteNumberContent);

    }

    private void initializePayrollTariffField() {

        PayrollTariff payrollTariff = (PayrollTariff) currentAbstractTariff;

        initializeIncludedTraffic(payrollTariff);
        initializePayroll(payrollTariff);

    }

    private void initializeIncludedTraffic(PayrollTariff payrollTariff) {

        boolean includedTrafficAttributeFlag = currentElement.hasAttribute(ATTRIBUTE_INCLUDED_TRAFFIC);
        IncludedTraffic includedTraffic = null;

        if (includedTrafficAttributeFlag) {
            String contentIncludedTraffic = currentElement.getAttribute(ATTRIBUTE_INCLUDED_TRAFFIC);
            String upperContent = contentIncludedTraffic.toUpperCase();
            includedTraffic = IncludedTraffic.valueOf(upperContent);
        } else {
            includedTraffic = IncludedTraffic.ONE;
        }

        payrollTariff.setIncludedTraffic(includedTraffic);

    }

    private void initializePayroll(PayrollTariff payrollTariff) {

        String payrollContent = getElementTextContent(TAG_ELEMENT_PAYROLL);
        int payroll = Integer.parseInt(payrollContent);
        payrollTariff.setPayroll(payroll);

    }

    private String getElementTextContent(String tagName) {

        NodeList nList = currentElement.getElementsByTagName(tagName);
        Node node = nList.item(0);

        return node.getTextContent();
    }

}