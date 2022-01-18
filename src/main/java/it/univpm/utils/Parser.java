package it.univpm.utils;

import it.univpm.services.ChiamataAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <b>Classe astratta</b> contenente il nome della citta' e la nazione. Inoltre contiene un metodo astratto che si occupa della lettura dei dati e un metodo che chiama l'API di Openweather ed effettua il parsing ritornando il JSONObject corrispondente.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public abstract class Parser {

    private String nomeCitta;
    private String nazione;

    /**
     * <b>Costruttore</b> della classe <code>Parser</code>.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> a cui appartiene la citta'.
     */
    public Parser(String nomeCitta, String nazione) {
        this.nomeCitta = nomeCitta;
        this.nazione = nazione;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nomeCitta</code>.
     *
     * @return String nomeCitta
     */
    public String getNomeCitta() {
        return nomeCitta;
    }

    /**
     * <b>Setter</b> dell'attributo <code>nomeCitta</code>.
     *
     * @param nomeCitta <b>Nome</b> della città.
     */
    public void setNomeCitta(String nomeCitta) {
        this.nomeCitta = nomeCitta;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nazione</code>.
     *
     * @return String nazione
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * <b>Setter</b> dell'attributo <code>nazione</code>.
     *
     * @param nazione <b>Nazione</b> a cui appartiene la città.
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * <b>Metodo astratto</b> implementato nelle sottoclassi. Questo metodo permette di leggere un JSONOBject per estrarne i dati.
     *
     * @param <T> Template del tipo di ritorno delle implementazioni di questo metodo nelle sottoclassi. Può essere qualunque tipo.
     * @return <code>JSONObject</code> - JSONObject contenente i dati ottenuti dalla chiamata all'API di OpenWeather.
     * @throws IOException    Eccezione relativa all'input/output.
     * @throws ParseException Eccezione relativa al parsing.
     */
    public abstract <T> T leggiDati() throws IOException, ParseException;


    /**
     * <b>Metodo</b> che si occupa di effettuare il parsing del file fornito dalla ChiamataAPI e restituirlo come JSONObject.
     *
     * @return <code>JSONObject</code> - JSONObject contenente il risultato del parsing del file fornito dalla ChiamataAPI.
     * @throws IOException    Eccezione relativa all'input/output.
     * @throws ParseException Eccezione relativa al parsing.
     */
    protected JSONObject getRispostaAPI() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        BufferedReader input = new BufferedReader(new InputStreamReader(ChiamataAPI.chiamaAPI(this.nomeCitta, this.nazione).getInputStream()));
        String in = input.readLine();

        // TODO inserisci exception personalizzate e non per i codici di errore di openWeather

        return (JSONObject) parser.parse(in);
    }
}
