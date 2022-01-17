package it.univpm.dataAnalytics.stats;

/**
 * <b>Interfaccia</b> contenente i metodi astratti necessari per il calcolo delle statistiche.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public interface StatisticheInterface {

    /**
     * <b>Metodo</b> <i>astratto</i> che si occupera' di calcolare il minimo di una serie di valori.
     *
     * @return <code>double</code> - Minimo di una serie di valori.
     */
    double getMinimo ();

    /**
     * <b>Metodo</b> <i>astratto</i> che si occupera' di calcolare il massimo di una serie di valori.
     *
     * @return <code>double</code> - Massimo di una serie di valori.
     */
    double getMassimo ();

    /**
     * <b>Metodo</b> <i>astratto</i> che si occupera' di calcolare la media aritmetica di una serie di valori.
     *
     * @return <code>double</code> - Media di una serie di valori.
     */
    double getMedia ();

    /**
     * <b>Metodo</b> <i>astratto</i> che si occupera' di calcolare la varianza di una serie di valori.
     *
     * @return <code>double</code> - Varianza di una serie di valori.
     */
    double getVarianza ();


}
