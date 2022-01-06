package it.univpm.services;

public class ChiamataAPI {
    private final String chiaveAPI = "30c83cda5c0f4144140a7be526862c1f";
    private String nomeCitta;
    private String nazione;

    public ChiamataAPI(String nomeCitta, String nazione) {
        this.nomeCitta = nomeCitta;
        this.nazione = nazione;
    }

    public String getChiaveAPI() {
        return chiaveAPI;
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

    public String generatoreURL(){
        return "api.openweathermap.org/data/2.5/forecast?appid=" + this.chiaveAPI + "&q=" + this.nomeCitta + "&country=" + this.nazione;
    }
}
