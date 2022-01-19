package it.univpm.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TestParserCittaException {

    ParserCitta parserException;

    @BeforeEach
    void setUp() {
        parserException = new ParserCitta("aaaaaaa", "aaaaaaaa");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testLeggiDatiException() {
        assertThrows(FileNotFoundException.class, () -> parserException.leggiDati());
    }
}