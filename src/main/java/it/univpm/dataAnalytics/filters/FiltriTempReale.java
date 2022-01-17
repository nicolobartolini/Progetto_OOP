package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.models.Citta;
import it.univpm.models.Data;
import it.univpm.models.Temperatura;

import java.util.Vector;

public class FiltriTempReale implements FiltriInterface{

    private Citta citta;

    public FiltriTempReale(Citta citta) {
        this.citta = citta;
    }

    public double getMinimoGiornaliero(Data giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    public double getMassimoGiornaliero(Data giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    public double getMediaGiornaliera(Data giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    public double getVarianzaGiornaliera(Data giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    public double getMinimoFasciaOraria(Data oraIniziale, Data oraFinale) {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    public double getMassimoFasciaOraria(Data oraIniziale, Data oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    public double getMediaFasciaOraria(Data oraIniziale, Data oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    public double getVarianzaFasciaOraria(Data oraIniziale, Data oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    private boolean isGiornoValido(Data giorno) {
        return !giorno.getDate().before(citta.getPrimaOccorrenzaValori().getDate())
                && !giorno.getDate().after(citta.getUltimaOccorrenzaValori().getDate());
    }

    private StatisticheTempReale getStatisticheGiornaliere(Data giorno) {
        if(!isGiornoValido(giorno)){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDay() == giorno.getDate().getDay())
                vTempReale.add(t);
        }
        return new StatisticheTempReale(vTempReale);
    }

    private boolean isFasciaOrariaValida(Data oraIniziale, Data oraFinale) {
        return (!oraIniziale.getDate().before(citta.getPrimaOccorrenzaValori().getDate())
                && !oraFinale.getDate().after(citta.getUltimaOccorrenzaValori().getDate()))
                && !oraFinale.getDate().before(oraIniziale.getDate())
                && !oraIniziale.getDate().equals(oraFinale.getDate())
                && oraIniziale.getDate().getDay() == oraFinale.getDate().getDay();
    }

    private StatisticheTempReale getStatisticheFasciaOraria(Data oraIniziale, Data oraFinale) {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            // TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDay() == oraIniziale.getDate().getDay() && t.getData().getDate().getDay() == oraFinale.getDate().getDay()) {
                if (t.getData().getDate().after(oraIniziale.getDate()) && t.getData().getDate().before(oraFinale.getDate()))
                    vTempReale.add(t);
            }
        }
        return new StatisticheTempReale(vTempReale);
    }
}
