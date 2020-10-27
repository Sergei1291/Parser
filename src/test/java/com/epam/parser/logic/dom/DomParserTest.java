package com.epam.parser.logic.dom;

import com.epam.parser.logic.AbstractParserTest;
import com.epam.parser.logic.Parser;

public class DomParserTest extends AbstractParserTest {

    @Override
    public Parser create() {

        return new DomParser();
    }

}