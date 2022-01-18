package it.univpm.models;

/**
 * <b>Classe</b> contenente le coordinate di una citta' sotto forma di latitudine e longitudine.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Coordinate {

    private double latitudine;
    private double longitudine;

    /**
     * <b>Costruttore</b> della classe <code>Coordinate</code>.
     *
     * @param latitudine  <i>Latitudine</i> della citta'.
     * @param longitudine <i>Longitudine</i> della citta'.
     */
    public Coordinate(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    /**
     * <b>Getter</b> dell'attributo <code>latitudine</code>.
     *
     * @return double latitudine
     */
    public double getLatitude() {
        return latitudine;
    }

    /**
     * <b>Setter</b> dell'attributo <code>latitudine</code>.
     *
     * @param latitudine <i>Latitudine</i> della citta'.
     */
    public void setLatitude(double latitudine) {
        this.latitudine = latitudine;
    }

    /**
     * <b>Getter</b> dell'attributo <code>longitudine</code>.
     *
     * @return double longitudine
     */
    public double getLongitude() {
        return longitudine;
    }

    /**
     * <b>Setter</b> dell'attributo <code>longitudine</code>.
     *
     * @param longitudine <i>Longitudine</i> della citta'.
     */
    public void setLongitude(double longitudine) {
        this.longitudine = longitudine;
    }
}
