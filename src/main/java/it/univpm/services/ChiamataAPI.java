package it.univpm.services;

import java.net.URL;
import java.net.URLConnection;

public class ChiamataAPI {
    private final static String CHIAVE_API = "30c83cda5c0f4144140a7be526862c1f";

    public ChiamataAPI() {
    }

    public String getChiaveAPI() {
        return CHIAVE_API;
    }

    public static String generatoreURL(String nomeCitta, String nazione){
        return "https://api.openweathermap.org/data/2.5/forecast?appid=" + CHIAVE_API + "&q=" + nomeCitta + "&country=" + nazione;
    }

    public static URLConnection chiamaAPI(String nomeCitta, String nazione) throws java.io.IOException {
        return new URL(generatoreURL(nomeCitta, nazione)).openConnection();
    }
}
