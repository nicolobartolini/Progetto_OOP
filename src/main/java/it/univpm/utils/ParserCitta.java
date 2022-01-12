package it.univpm.utils;

import it.univpm.models.Citta;
import it.univpm.models.Coordinate;
import it.univpm.models.Pressione;
import it.univpm.models.Temperatura;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Vector;

/**
 * <b>Classe</b> figlia di {@link #Parser} che permette di effettuare il parsing dei dati di una città.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class ParserCitta extends Parser{

    /**
     * <b>Costruttore</b> della classe <code>ParserCitta</code>. Richiama il costruttore della superclasse.
     * @param nomeCitta <b>Nome</b> della città.
     * @param nazione <b>Nazione</b> a cui appartiene la città.
     */
    public ParserCitta(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    /**
     * <i>Implementazione</i> del <b>metodo astratto</b> <code>leggiDati</code>. Effettua il parsing del JSONOBject di OpenWeather per restituire un istanza della classe Citta contenente i dati relativi a una città.
     * @return Oggetto <code>Citta</code> contenente i dati relativi a una città.
     * @throws java.io.IOException Eccezione relativo all'input/output.
     * @throws ParseException Eccezione relativa al parsing.
     */
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
        ParserPressione parsePress = new ParserPressione(getNomeCitta(), getNazione());
        Vector<Pressione> pressioni = parsePress.leggiDati();

        return new Citta(id, nome, nazione, coord, pressioni, temperature);

    }
}
