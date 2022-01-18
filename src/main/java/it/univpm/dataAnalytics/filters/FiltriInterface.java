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

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare il minimo dei valori relativi a un determinato giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>double</code> - Minimo della serie di valori relativi al giorno richiesto.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMinimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare il massimo dei valori relativi a un determinato giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>double</code> - Massimo della serie di valori relativi al giorno richiesto.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMassimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare la media dei valori relativi a un determinato giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>double</code> - Media della serie di valori relativi al giorno richiesto.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMediaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare la varianza dei valori relativi a un determinato giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>double</code> - Varianza della serie di valori relativi al giorno richiesto.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getVarianzaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare il minimo dei valori relativi a una determinata fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>double</code> - Minimo della serie di valori relativi alla fascia oraria richiesta.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare il massimo dei valori relativi a una determinata fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>double</code> - Massimo della serie di valori relativi alla fascia oraria richiesta.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare la media dei valori relativi a una determinata fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>double</code> - Media della serie di valori relativi alla fascia oraria richiesta.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;

    /**
     * <b>Metodo</b> astratto che si occupera' di calcolare la varianza dei valori relativi a una determinata fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>double</code> - Varianza della serie di valori relativi alla fascia oraria richiesta.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException;
}
