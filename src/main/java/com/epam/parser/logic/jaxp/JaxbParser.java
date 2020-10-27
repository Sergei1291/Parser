package com.epam.parser.logic.jaxp;

import com.epam.parser.model.AbstractTariff;
import com.epam.parser.model.Tariffs;
import com.epam.parser.logic.Parser;
import com.epam.parser.logic.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JaxbParser implements Parser {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public List<AbstractTariff> parse(String fileName) throws ParserException {

        FileReader reader = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reader = new FileReader(fileName);
            Tariffs tariffs = (Tariffs) unmarshaller.unmarshal(new File(fileName));

            return tariffs.getAbstractTariffs();

        } catch (JAXBException | FileNotFoundException e) {
            throw new ParserException(e);
        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }

    }

}