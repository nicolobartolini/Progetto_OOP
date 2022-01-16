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
        if(giorno.getDate().before(citta.getPrimaOccorrenzaValori().getDate())|| giorno.getDate().after(citta.getUltimaOccorrenzaValori().getDate())){
            //TODO eccezione

        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDay() == giorno.getDate().getDay())
                vTempReale.add(t);
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMinimo();
    }

    public double getMassimoGiornaliero(Data giorno){
        if(giorno.getDate().before(citta.getPrimaOccorrenzaValori().getDate())|| giorno.getDate().after(citta.getUltimaOccorrenzaValori().getDate())){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDay() == giorno.getDate().getDay())
                vTempReale.add(t);
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMassimo();
    }

    public double getMediaGiornaliera(Data giorno){
        if(giorno.getDate().before(citta.getPrimaOccorrenzaValori().getDate())|| giorno.getDate().after(citta.getUltimaOccorrenzaValori().getDate())){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDay() == giorno.getDate().getDay())
                vTempReale.add(t);
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMedia();
    }

    public double getVarianzaGiornaliera(Data giorno){
        if(giorno.getDate().before(citta.getPrimaOccorrenzaValori().getDate())|| giorno.getDate().after(citta.getUltimaOccorrenzaValori().getDate())){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDay() == giorno.getDate().getDay())
                vTempReale.add(t);
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getVarianza();
    }

    public double getMinimoFasciaOraria(Data oraIniziale, Data oraFinale) {
        if ((oraIniziale.getDate().before(citta.getPrimaOccorrenzaValori().getDate()) || oraFinale.getDate().after(citta.getUltimaOccorrenzaValori().getDate()))
                || oraFinale.getDate().before(oraIniziale.getDate())
                || oraIniziale.getDate().equals(oraFinale.getDate())
                || oraIniziale.getDate().getDay() != oraFinale.getDate().getDay()) {
            //TODO eccezione

        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDay() == oraIniziale.getDate().getDay() && t.getData().getDate().getDay() == oraFinale.getDate().getDay()) {
                if (t.getData().getDate().after(oraIniziale.getDate()) && t.getData().getDate().before(oraFinale.getDate()))
                    vTempReale.add(t);
            }
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMinimo();
    }

    public double getMassimoFasciaOraria(Data oraIniziale, Data oraFinale){
        if ((oraIniziale.getDate().before(citta.getPrimaOccorrenzaValori().getDate()) || oraFinale.getDate().after(citta.getUltimaOccorrenzaValori().getDate()))
                || oraFinale.getDate().before(oraIniziale.getDate())
                || oraIniziale.getDate().equals(oraFinale.getDate())
                || oraIniziale.getDate().getDay() != oraFinale.getDate().getDay()) {
            //TODO eccezione

        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDay() == oraIniziale.getDate().getDay() && t.getData().getDate().getDay() == oraFinale.getDate().getDay()) {
                if (t.getData().getDate().after(oraIniziale.getDate()) && t.getData().getDate().before(oraFinale.getDate()))
                    vTempReale.add(t);
            }
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMassimo();
    }

    public double getMediaFasciaOraria(Data oraIniziale, Data oraFinale){
        if ((oraIniziale.getDate().before(citta.getPrimaOccorrenzaValori().getDate()) || oraFinale.getDate().after(citta.getUltimaOccorrenzaValori().getDate()))
                || oraFinale.getDate().before(oraIniziale.getDate())
                || oraIniziale.getDate().equals(oraFinale.getDate())
                || oraIniziale.getDate().getDay() != oraFinale.getDate().getDay()) {
            //TODO eccezione

        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDay() == oraIniziale.getDate().getDay() && t.getData().getDate().getDay() == oraFinale.getDate().getDay()) {
                if (t.getData().getDate().after(oraIniziale.getDate()) && t.getData().getDate().before(oraFinale.getDate()))
                    vTempReale.add(t);
            }
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getMedia();
    }

    public double getVarianzaFasciaOraria(Data oraIniziale, Data oraFinale){
        if ((oraIniziale.getDate().before(citta.getPrimaOccorrenzaValori().getDate()) || oraFinale.getDate().after(citta.getUltimaOccorrenzaValori().getDate()))
                || oraFinale.getDate().before(oraIniziale.getDate())
                || oraIniziale.getDate().equals(oraFinale.getDate())
                || oraIniziale.getDate().getDay() != oraFinale.getDate().getDay()) {
            //TODO eccezione

        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDay() == oraIniziale.getDate().getDay() && t.getData().getDate().getDay() == oraFinale.getDate().getDay()) {
                if (t.getData().getDate().after(oraIniziale.getDate()) && t.getData().getDate().before(oraFinale.getDate()))
                    vTempReale.add(t);
            }
        }
        StatisticheTempReale stat = new StatisticheTempReale(vTempReale);
        return stat.getVarianza();
    }
}
