package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.models.Citta;
import it.univpm.models.Data;
import it.univpm.models.Temperatura;

import java.time.LocalDateTime;
import java.util.Vector;

public class FiltriTempReale extends FiltriTemp implements FiltriInterface{

    public FiltriTempReale(Citta citta) {
        super(citta);
    }

    public double getMinimoGiornaliero(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    public double getMassimoGiornaliero(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    public double getMediaGiornaliera(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    public double getVarianzaGiornaliera(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    public double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    public double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    public double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    public double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    private boolean isGiornoValido(LocalDateTime giorno) {
        return !giorno.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !giorno.isAfter(citta.getUltimaOccorrenzaValori().getDate());
    }

    private StatisticheTempReale getStatisticheGiornaliere(LocalDateTime giorno) {
        if(!isGiornoValido(giorno)){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDayOfMonth() == giorno.getDayOfMonth())
                vTempReale.add(t);
        }
        return new StatisticheTempReale(vTempReale);
    }

    private boolean isFasciaOrariaValida(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        return (!oraIniziale.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !oraFinale.isAfter(citta.getUltimaOccorrenzaValori().getDate()))
                && !oraFinale.isBefore(oraIniziale)
                && !oraIniziale.equals(oraFinale)
                && oraIniziale.getDayOfMonth() == oraFinale.getDayOfMonth();
    }

    private StatisticheTempReale getStatisticheFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            // TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == oraIniziale.getDayOfMonth() && t.getData().getDate().getDayOfMonth() == oraFinale.getDayOfMonth()) {
                if (t.getData().getDate().isAfter(oraIniziale) && t.getData().getDate().isBefore(oraFinale))
                    vTempReale.add(t);
            }
        }
        return new StatisticheTempReale(vTempReale);
    }
}
