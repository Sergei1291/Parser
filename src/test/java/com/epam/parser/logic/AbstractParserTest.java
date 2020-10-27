package com.epam.parser.logic;

import com.epam.parser.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {

    private final static String DATA = "src/test/resources/tariffs.xml";
    private final static String FILE_NOT_EXIST = "src/tariffs.xml";

    private final static AbstractTariff ABSTRACT_TARIFF_ONE = new Tariff("First-tariff-1", new Price(10, 1, 1), "112223344");
    private final static AbstractTariff ABSTRACT_TARIFF_TWO = new Tariff("Tariff-second", new Price(20, 2, 2), "556667788");
    private final static AbstractTariff ABSTRACT_TARIFF_THREE = new PayrollTariff("SimplePayroll", new Price(30, 3, 3), 300, IncludedTraffic.ONE);
    private final static AbstractTariff ABSTRACT_TARIFF_FOUR = new PayrollTariff("Internet-16", new Price(40, 4, 4), 400, IncludedTraffic.SIXTEEN);

    private final static List<AbstractTariff> ABSTRACT_TARIFFS = Arrays.asList(ABSTRACT_TARIFF_ONE, ABSTRACT_TARIFF_TWO, ABSTRACT_TARIFF_THREE, ABSTRACT_TARIFF_FOUR);

    public abstract Parser create();

    @Test
    public void testParseShouldCreateEqualListAbstractTariffsWhenDataFile() throws ParserException {
        //given
        Parser parser = create();
        //when
        List<AbstractTariff> actual = parser.parse(DATA);
        //then
        Assert.assertEquals(ABSTRACT_TARIFFS, actual);
    }

    @Test(expected = ParserException.class)//then
    public void testParseShouldThrowParserExceptionWhenFileNotExist() throws ParserException {
        //given
        Parser parser = create();
        //when
        parser.parse(FILE_NOT_EXIST);
    }

}