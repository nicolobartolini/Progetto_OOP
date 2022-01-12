package it.univpm.utils;

import it.univpm.models.Temperatura;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

/**
 * <b>Classe</b> figlia di {@link #Parser} che permette di effettuare il parsing dei dati relativi alle temperature di una città.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class ParserTemperatura extends Parser{

    /**
     * <b>Costruttore</b> della classe <code>ParserTemperatura</code>. Richiama il costruttore della superclasse.
     * @param nomeCitta <b>Nome</b> della città.
     * @param nazione <b>Nazione</b> a cui appartiene la città.
     */
    public ParserTemperatura(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    /**
     * <i>Implementazione</i> del <b>metodo astratto</b> <code>leggiDati</code>. Effettua il parsing del JSONOBject di OpenWeather per restituire un vettore d'istanze della classe Temperatura.
     * @return Vettore <code>temperature</code> contenente le varie previsioni delle temperature.
     * @throws java.io.IOException Eccezione relativo all'input/output.
     * @throws ParseException Eccezione relativa al parsing.
     */
    public Vector<Temperatura> leggiDati() throws IOException, ParseException {

        Vector<Temperatura> temperature = new Vector<Temperatura>();
        JSONObject rispostaAPI = getRispostaAPI();
        JSONArray listaAPI = (JSONArray) rispostaAPI.get("list");

        for (Object o : listaAPI) {
            JSONObject obj = (JSONObject) o;
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

    /**
     * <b>Metodo statico</b> necessario per effettuare il parsing dei valori di tipo <code>double</code> all'interno del metodo {@link #leggiDati()}. Senza l'utilizzo di questo metodo il parsing porterebbe a una <code>ClassCastException</code>.
     * @param value Valore da controllare.
     * @return Valore <code>value</code> prima castato a <code>Number</code>, poi a <code>double</code>.
     */
    private static double doubleValue(Object value) {
        return (value instanceof Number ? ((Number)value).doubleValue() : -1.0);
    }
}
