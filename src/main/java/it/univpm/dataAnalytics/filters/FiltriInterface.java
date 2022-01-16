package it.univpm.dataAnalytics.filters;

import it.univpm.models.Data;

public interface FiltriInterface {

    public abstract double getMinimoGiornaliero(Data giorno);

    public abstract double getMassimoGiornaliero(Data giorno);

    public abstract double getMediaGiornaliera(Data giorno);

    public abstract double getVarianzaGiornaliera(Data giorno);

    public abstract double getMinimoFasciaOraria(Data oraIniziale, Data oraFinale);

    public abstract double getMassimoFasciaOraria(Data oraIniziale, Data oraFinale);

    public abstract double getMediaFasciaOraria(Data oraIniziale, Data oraFinale);

    public abstract double getVarianzaFasciaOraria(Data oraIniziale, Data oraFinale);
}
