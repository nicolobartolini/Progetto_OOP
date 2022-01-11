package it.univpm.utils;

import it.univpm.models.Temperatura;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

public class ParserTemperatura extends Parser{

    public ParserTemperatura(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    @Override
    public Vector<Temperatura> leggiDati() throws IOException, ParseException {

        Vector<Temperatura> temperature = new Vector<Temperatura>();
        JSONObject rispostaAPI = getRispostaAPI();
        JSONArray listaAPI = (JSONArray) rispostaAPI.get("list");

        for (int i = 0; i < listaAPI.size(); i++) {
            JSONObject obj = (JSONObject) listaAPI.get(i);
            long dataEpoch = (long) obj.get("dt");
            JSONObject main = (JSONObject) obj.get("main");
            double valoreReale = doubleValue(main.get("temp"));
            double valorePercepito = doubleValue(main.get("feels_like"));
            double valoreMinimo = doubleValue(main.get("temp_min"));
            double valoreMassimo = doubleValue(main.get("temp_max"));
            Temperatura nuovaTemp = new Temperatura(valoreReale, valorePercepito, valoreMinimo, valoreMassimo, dataEpoch);
            temperature.add(nuovaTemp);
        }
        return temperature;
    }

    private static double doubleValue(Object value) {
        return (value instanceof Number ? ((Number)value).doubleValue() : -1.0);
    }
}
