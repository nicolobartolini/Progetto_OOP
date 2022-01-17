package it.univpm.dataAnalytics.filters;

import java.time.LocalDateTime;

/**
 * <b>Interfaccia</b> contenente i metodi astratti necessari per il filtraggio dei valori.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
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
