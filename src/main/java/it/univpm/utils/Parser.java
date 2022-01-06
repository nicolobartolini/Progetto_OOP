package it.univpm.utils;

import it.univpm.models.Citta;
import it.univpm.models.Pressione;
import it.univpm.models.Temperatura;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface Parser {

    public abstract <T> T leggiDati(String nomeCitta, String nazione) throws IOException, ParseException;
}
