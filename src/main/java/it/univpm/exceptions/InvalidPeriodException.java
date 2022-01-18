package it.univpm.exceptions;

/**
 * Eccezione relativa all'immissione di un periodo non valido.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 *
 */
public class InvalidPeriodException extends Exception {

    /**
     * Costruttore per InvalidPeriodException.
     */
    public InvalidPeriodException() {
        super("Il giorno o la fascia oraria immessi non sono validi.");
    }
}
