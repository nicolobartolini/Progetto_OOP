package it.univpm.services;

import java.net.URL;
import java.net.URLConnection;

/**
 * <b>Classe</b> che si occupa del servizio di chiamata API.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */

public class ChiamataAPI {

    /**
     *  <b>CHIAVE_API</b> è la chiave utilizzata per ricevere informazioni da OpenWeather.
     */
    private final static String CHIAVE_API = "30c83cda5c0f4144140a7be526862c1f";

    /**
     * <b>Costruttore</b> della classe <code>ChiamataAPI</code>.
     */
    public ChiamataAPI() {
    }

    /**
     * <b>Getter</b> dell'attributo <code>CHIAVE_API</code>.
     * @return CHIAVE_API
     */
    public String getChiaveAPI() {
        return CHIAVE_API;
    }

    /**
     * Metodo che si occupa di generare l'URL relativo alla città da cercare.
     * @param nomeCitta nome della città della quale si vogliono sapere le previsioni.
     * @param nazione nome della nazione di cui fa parte la città che si vuole cercare.
     * @return String URL generato
     */
    public static String generatoreURL(String nomeCitta, String nazione){
        return "https://api.openweathermap.org/data/2.5/forecast?appid=" + CHIAVE_API + "&q=" + nomeCitta + "&country=" + nazione;
    }

    /**
     * Metodo che si occupa della chiamata delle API di OpenWeather tramite l'URL generato dal metodo generatoreURL.
     * @param nomeCitta nome della città della quale si vogliono sapere le previsioni.
     * @param nazione nome della nazione di cui fa parte la città che si vuole cercare.
     * @return connessione all'URL dell'API che contiene un file JSON.
     * @throws java.io.IOException eccezione relativa all'input/output.
     */
    public static URLConnection chiamaAPI(String nomeCitta, String nazione) throws java.io.IOException {
        return new URL(generatoreURL(nomeCitta, nazione)).openConnection();
    }
}
