package it.univpm.utils;

import it.univpm.models.Citta;
import it.univpm.models.Coordinate;
import it.univpm.services.ChiamataAPI;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ParserCitta implements Parser{

    public Citta leggiDati(String nomeCitta, String nazioneCitta) throws java.io.IOException, ParseException {

        JSONParser parser = new JSONParser();
        ChiamataAPI api = new ChiamataAPI(nomeCitta, nazioneCitta);
        BufferedReader input = new BufferedReader(new InputStreamReader(api.chiamaAPI().getInputStream()));
        String in = input.readLine();

        // TODO inserisci exception personalizzate e non per i codici di errore di openWeather

        JSONObject rispostaAPI = (JSONObject) parser.parse(in);
        JSONObject cittaAPI = (JSONObject) rispostaAPI.get("city");
        long id = (long) cittaAPI.get("id");
        String nome = (String) cittaAPI.get("name");
        String nazione = (String) cittaAPI.get("country");
        JSONObject coordAPI = (JSONObject) cittaAPI.get("coord");
        double lat = (double) coordAPI.get("lat");
        double lon = (double) coordAPI.get("lon");
        Coordinate coord = new Coordinate(lat, lon);

        return null;
    }
}
