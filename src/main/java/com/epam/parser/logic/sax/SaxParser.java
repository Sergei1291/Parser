package com.epam.parser.logic.sax;

import com.epam.parser.model.AbstractTariff;
import com.epam.parser.logic.Parser;
import com.epam.parser.logic.ParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    private TariffHandler tariffHandler;

    public SaxParser(TariffHandler tariffHandler) {

        this.tariffHandler = tariffHandler;

    }

    @Override
    public List<AbstractTariff> parse(String fileName) throws ParserException {

        List<AbstractTariff> abstractTariffs = null;

        try {

            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tariffHandler);
            reader.parse(fileName);

            abstractTariffs = tariffHandler.getAbstractTariffs();

        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }

        return abstractTariffs;
    }

}