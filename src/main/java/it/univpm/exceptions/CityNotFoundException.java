package it.univpm.exceptions;

/**
 * Eccezione relativa all'immissione di una città non esistente.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 *
 */
public class CityNotFoundException extends Exception {

    /**
     * Costruttore per CityNotFoundException.
     */
    public CityNotFoundException() {
        super("Città non trovata.");
    }
}
