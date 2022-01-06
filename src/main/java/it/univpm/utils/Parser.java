package it.univpm.utils;

import it.univpm.services.ChiamataAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Parser {

    private String nomeCitta;
    private String nazione;

    public Parser(String nomeCitta, String nazione) {
        this.nomeCitta = nomeCitta;
        this.nazione = nazione;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }

    public void setNomeCitta(String nomeCitta) {
        this.nomeCitta = nomeCitta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public abstract <T> T leggiDati() throws IOException, ParseException;

    protected JSONObject getRispostaAPI() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        ChiamataAPI api = new ChiamataAPI(this.nomeCitta, this.nazione);
        BufferedReader input = new BufferedReader(new InputStreamReader(api.chiamaAPI().getInputStream()));
        String in = input.readLine();

        // TODO inserisci exception personalizzate e non per i codici di errore di openWeather

        JSONObject rispostaAPI = (JSONObject) parser.parse(in);
        return rispostaAPI;
    }
}
