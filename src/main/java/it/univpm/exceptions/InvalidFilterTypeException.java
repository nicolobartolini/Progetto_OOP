package it.univpm.exceptions;

/**
 * Eccezione relativa all'immissione di un tipo di filtraggio non esistente.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class InvalidFilterTypeException extends Exception {

    /**
     * Costruttore per InvalidFilterTypeException.
     */
    public InvalidFilterTypeException() {
        super("Il tipo di filtraggio richiesto (tipoFiltro) non è valido, utilizzare [giornaliero|fasciaOraria]");
    }
}
