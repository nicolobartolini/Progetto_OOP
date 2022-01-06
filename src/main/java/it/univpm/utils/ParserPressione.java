package it.univpm.utils;

import it.univpm.models.Pressione;
import it.univpm.models.Temperatura;
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
            int valorePressione = (int) main.get("pressure");
            int valoreMare = (int) main.get("sea_level");
            int valoreSuolo = (int) main.get("grnd_level");
            Pressione nuovaPress = new Pressione(valorePressione, valoreMare, valoreSuolo, dataEpoch);
            pressioni.add(nuovaPress);
        }
        return pressioni;
    }
}
