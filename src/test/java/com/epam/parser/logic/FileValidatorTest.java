package com.epam.parser.logic;

import org.junit.Assert;
import org.junit.Test;

public class FileValidatorTest {

    private final static String VALID_FILE = "src/test/resources/tariffs.xml";
    private final static String INVALID_FILE = "src/test/resources/tariffsInvalid.xml";
    private final static String FILE_NOT_EXIST = "src/tariffs.xml";
    private final static String SCHEMA_FILE = "src/test/resources/tariffs.xsd";

    private FileValidator fileValidator = new FileValidator(SCHEMA_FILE);

    @Test
    public void testIsValidFileShouldTrueWhenFileContentValid() {
        //when
        boolean actual = fileValidator.isValidFile(VALID_FILE);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidFileShouldFalseWhenFileContentInvalid() {
        //when
        boolean actual = fileValidator.isValidFile(INVALID_FILE);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidFileShouldFalseWhenFileNotExist() {
        //when
        boolean actual = fileValidator.isValidFile(FILE_NOT_EXIST);
        //then
        Assert.assertFalse(actual);
    }

}