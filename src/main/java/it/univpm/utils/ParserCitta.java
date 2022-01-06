package it.univpm.utils;

import it.univpm.models.Citta;
import it.univpm.models.Coordinate;
import it.univpm.models.Temperatura;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Vector;

public class ParserCitta extends Parser{

    public ParserCitta(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    public Citta leggiDati() throws java.io.IOException, ParseException {

        JSONObject rispostaAPI = getRispostaAPI();
        JSONObject cittaAPI = (JSONObject) rispostaAPI.get("city");
        long id = (long) cittaAPI.get("id");
        String nome = (String) cittaAPI.get("name");
        String nazione = (String) cittaAPI.get("country");
        JSONObject coordAPI = (JSONObject) cittaAPI.get("coord");
        double lat = (double) coordAPI.get("lat");
        double lon = (double) coordAPI.get("lon");
        Coordinate coord = new Coordinate(lat, lon);
        ParserTemperatura parseTemp = new ParserTemperatura(getNomeCitta(), getNazione());
        Vector<Temperatura> temperature = parseTemp.leggiDati();

        return null;
    }
}
