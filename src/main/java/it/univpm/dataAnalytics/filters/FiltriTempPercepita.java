package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempPercepita;
import it.univpm.models.Citta;
import it.univpm.models.Data;
import it.univpm.models.Temperatura;

import java.time.LocalDateTime;
import java.util.Vector;

public class FiltriTempPercepita implements FiltriInterface{
    private Citta citta;

    public FiltriTempPercepita(Citta citta) {
        this.citta = citta;
    }

    public double getMinimoGiornaliero(LocalDateTime giorno){
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    public double getMassimoGiornaliero(LocalDateTime giorno){
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    public double getMediaGiornaliera(LocalDateTime giorno){
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    public double getVarianzaGiornaliera(LocalDateTime giorno){
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    public double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    public double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    public double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    public double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    private boolean isGiornoValido(LocalDateTime giorno) {
        return !giorno.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !giorno.isAfter(citta.getUltimaOccorrenzaValori().getDate());
    }

    private StatisticheTempPercepita getStatisticheGiornaliere(LocalDateTime giorno) {
        if (!isGiornoValido(giorno)){
            //TODO eccezione

        }
        Vector<Temperatura> vTempPercepita = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDayOfMonth() == giorno.getDayOfMonth())
                vTempPercepita.add(t);
        }
        return new StatisticheTempPercepita(vTempPercepita);
    }

    private boolean isFasciaOrariaValida(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        return (!oraIniziale.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !oraFinale.isAfter(citta.getUltimaOccorrenzaValori().getDate()))
                && !oraFinale.isBefore(oraIniziale)
                && !oraIniziale.equals(oraFinale)
                && oraIniziale.getDayOfMonth() == oraFinale.getDayOfMonth();
    }

    private StatisticheTempPercepita getStatisticheFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            //TODO eccezione

        }
        Vector<Temperatura> vTempPercepita = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == oraIniziale.getDayOfMonth() && t.getData().getDate().getDayOfMonth() == oraFinale.getDayOfMonth()) {
                if (t.getData().getDate().isAfter(oraIniziale) && t.getData().getDate().isBefore(oraFinale))
                    vTempPercepita.add(t);
            }
        }
        return new StatisticheTempPercepita(vTempPercepita);
    }
}
