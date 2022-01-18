package it.univpm.utils;

import it.univpm.models.Pressione;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

/**
 * <b>Classe</b> figlia di <code>Parser</code> che permette di effettuare il parsing dei dati relativi alle pressioni di una citta'.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class ParserPressione extends Parser {

    /**
     * <b>Costruttore</b> della classe <code>ParserPressione</code>. Richiama il costruttore della superclasse.
     *
     * @param nomeCitta <b>Nome</b> della città.
     * @param nazione   <b>Nazione</b> a cui appartiene la città.
     */
    public ParserPressione(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    /**
     * <i>Implementazione</i> del <b>metodo astratto</b> <code>leggiDati</code>. Effettua il parsing del JSONObject di OpenWeather per restituire un vettore d'istanze della classe <code>Pressione</code>.
     *
     * @return <code>Vector</code> - Vettore contenente le varie previsioni delle pressioni.
     * @throws IOException    Eccezione relativo all'input/output.
     * @throws ParseException Eccezione relativa al parsing.
     */
    public Vector<Pressione> leggiDati() throws IOException, ParseException {

        Vector<Pressione> pressioni = new Vector<Pressione>();
        JSONObject rispostaAPI = getRispostaAPI();
        JSONArray listaAPI = (JSONArray) rispostaAPI.get("list");

        for (Object o : listaAPI) {
            JSONObject obj = (JSONObject) o;
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

    /**
     * <b>Metodo statico</b> necessario per effettuare il parsing dei valori di tipo <code>long</code> all'interno del metodo <code>leggiDati()</code>. Senza l'utilizzo di questo metodo il parsing porterebbe a una <code>ClassCastException</code>.
     *
     * @param value Valore da controllare.
     * @return <code>long</code> - Valore <code>value</code> prima castato a <code>Number</code>, poi a <code>long</code>.
     */
    private static long longValue(Object value) {
        return (value instanceof Number ? ((Number) value).longValue() : -1);
    }
}
