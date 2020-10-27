package com.epam.parser.logic;

import com.epam.parser.model.AbstractTariff;

import java.util.List;

public interface Parser {

    List<AbstractTariff> parse(String fileName) throws ParserException;

}