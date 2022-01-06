package it.univpm.utils;

import it.univpm.models.Pressione;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

public class ParserPressione implements Parser{

    private String nomeCitta;
    private String nazione;

    public ParserPressione(String nomeCitta, String nazione) {
        this.nomeCitta = nomeCitta;
        this.nazione = nazione;
    }
    @Override
    public Vector<Pressione> leggiDati() throws IOException, ParseException {
        return null;
    }
}
