package com.epam.parser.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class FileValidator {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private final String schemaName;

    public FileValidator(String schemaName) {

        this.schemaName = schemaName;

    }

    public boolean isValidFile(String fileName) {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(schemaName);
        boolean isValid = true;

        try {

            Schema schema = schemaFactory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);

        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            isValid = false;
        }

        return isValid;
    }

}