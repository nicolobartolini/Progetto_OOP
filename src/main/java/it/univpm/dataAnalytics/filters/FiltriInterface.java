package it.univpm.dataAnalytics.filters;

import it.univpm.models.Data;

import java.time.LocalDateTime;

public interface FiltriInterface {

    double getMinimoGiornaliero(LocalDateTime giorno);

    double getMassimoGiornaliero(LocalDateTime giorno);

    double getMediaGiornaliera(LocalDateTime giorno);

    double getVarianzaGiornaliera(LocalDateTime giorno);

    double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale);

    double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale);

    double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale);

    double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale);
}
