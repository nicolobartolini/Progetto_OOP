package it.univpm.utils;

import it.univpm.models.Citta;
import it.univpm.models.Coordinate;
import it.univpm.models.Pressione;
import it.univpm.models.Temperatura;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * <b>Classe</b> figlia di <code>Parser</code> che permette di effettuare il parsing dei dati di una citta'.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class ParserCitta extends Parser {

    /**
     * <b>Costruttore</b> della classe <code>ParserCitta</code>. Richiama il costruttore della superclasse.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> a cui appartiene la citta'.
     */
    public ParserCitta(String nomeCitta, String nazione) {
        super(nomeCitta, nazione);
    }

    /**
     * <i>Implementazione</i> del <b>metodo astratto</b> <code>leggiDati</code>. Effettua il parsing del JSONObject di OpenWeather per restituire un istanza della classe <code>Citta</code> contenente i dati relativi a una citta'.
     *
     * @return <code>Citta</code> - Istanza della classe <code>Citta</code> contenente i dati relativi a una citta'.
     * @throws java.io.IOException   Eccezione relativo all'input/output.
     * @throws ParseException        Eccezione relativa al parsing.
     * @throws FileNotFoundException Eccezione lanciata quando la città richiesta non viene trovata.
     */
    public Citta leggiDati() throws IOException, ParseException, FileNotFoundException {

        JSONObject rispostaAPI = getRispostaAPI();
        String cod = (String) rispostaAPI.get("cod");
        if (cod.equals("404")) {
            throw new FileNotFoundException();
        }
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
