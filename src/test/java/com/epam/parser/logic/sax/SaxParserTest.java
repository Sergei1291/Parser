package com.epam.parser.logic.sax;

import com.epam.parser.logic.AbstractParserTest;
import com.epam.parser.logic.Parser;

public class SaxParserTest extends AbstractParserTest {

    @Override
    public Parser create() {

        TariffHandler tariffHandler = new TariffHandler();

        return new SaxParser(tariffHandler);
    }

}