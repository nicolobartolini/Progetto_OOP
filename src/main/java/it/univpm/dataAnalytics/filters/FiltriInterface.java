package it.univpm.dataAnalytics.filters;

import it.univpm.exceptions.InvalidPeriodException;

import java.time.LocalDateTime;

/**
 * <b>Interfaccia</b> contenente i metodi astratti necessari per il filtraggio dei valori.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public interface FiltriInterface {

    double getMinimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException;

    double getMassimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException;

    double getMediaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException;

    double getVarianzaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException;

    double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;
}
