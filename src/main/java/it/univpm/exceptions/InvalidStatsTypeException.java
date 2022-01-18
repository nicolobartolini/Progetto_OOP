package it.univpm.exceptions;

/**
 * Eccezione relativa all'immissione di un tipo di dato sbagliato riguardante le statistiche.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class InvalidStatsTypeException extends Exception {

    /**
     * Costruttore per InvalidStatsTypeException.
     */
    public InvalidStatsTypeException() {
        super("Il tipo di dato sul quale sono richieste delle statistiche (tipoDato) non è valido, utilizzare [pressione|temperaturaReale|temperaturaPercepita]");
    }

}
