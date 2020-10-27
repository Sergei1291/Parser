package com.epam.parser.logic.jaxb;

import com.epam.parser.logic.AbstractParserTest;
import com.epam.parser.logic.Parser;
import com.epam.parser.logic.jaxp.JaxbParser;

public class JaxbParserTest extends AbstractParserTest {

    @Override
    public Parser create() {

        return new JaxbParser();
    }

}