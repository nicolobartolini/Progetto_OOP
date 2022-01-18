package it.univpm.exceptions;

/**
 * Eccezione relativa all'immissione di un tipo di dato sbagliato riguardante i filtri.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class InvalidTempTypeException extends Exception {

    /**
     * Costruttore per InvalidTempTypeException.
     */
    public InvalidTempTypeException() {
        super("Il tipo di dato sul quale è richiesto un filtraggio (tipoTemp) non è valido, utilizzare [reale|percepita]");
    }
}
