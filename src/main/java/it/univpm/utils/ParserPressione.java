package it.univpm.utils;

import it.univpm.models.Pressione;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

public class ParserPressione extends Parser{

    public ParserPressione(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    @Override
    public Vector<Pressione> leggiDati() throws IOException, ParseException {

        Vector<Pressione> pressioni = new Vector<Pressione>();
        JSONObject rispostaAPI = getRispostaAPI();
        JSONArray listaAPI = (JSONArray) rispostaAPI.get("list");

        for (int i = 0; i < listaAPI.size(); i++) {
            JSONObject obj = (JSONObject) listaAPI.get(i);
            long dataEpoch = (long) obj.get("dt");
            JSONObject main = (JSONObject) obj.get("main");
            long valorePressione = longValue(main.get("pressure"));
            long valoreMare = longValue(main.get("sea_level"));
            long valoreSuolo = longValue(main.get("grnd_level"));
            Pressione nuovaPress = new Pressione(valorePressione, valoreMare, valoreSuolo, dataEpoch);
            pressioni.add(nuovaPress);
        }
        return pressioni;
    }

    private static long longValue(Object value) {
        return (value instanceof Number ? ((Number)value).longValue() : -1);
    }
}
