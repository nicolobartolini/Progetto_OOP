package it.univpm.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>Classe</b> contenente una data, sia nel suo formato "Epoch" che nel suo formato di stringa "leggibile".
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Data {

    private long dataEpoch;
    private Date date;
    private String dataFormattata;

    /**
     * <b>Costruttore</b> della classe <code>data</code>.
     * @param dataEpoch data nel formato "Epoch".
     */
    public Data(long dataEpoch) {
        this.dataEpoch = dataEpoch;
        this.date = new Date(dataEpoch * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.dataFormattata = sdf.format(this.date);
    }

    /**
     * <b>Getter</b> dell'attributo <code>dataEpoch</code>.
     * @return long dataEpoch.
     */
    public long getDataEpoch() {
        return dataEpoch;
    }

    /**
     * <b>Setter</b> dell'attributo <code>dataEpoch</code>.
     * @param dataEpoch data nel formato "Epoch".
     */
    public void setDataEpoch(long dataEpoch) {
        this.dataEpoch = dataEpoch;
    }

    /**
     * <b>Getter</b> dell'attributo <code>data</code>.
     * @return Date data.
     */
    public Date getDate() {
        return date;
    }
    /**
     * <b>Setter</b> dell'attributo <code>data</code>.
     *
     * @param date data (istanza della classe Date di Java).
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * <b>Getter</b> dell'attributo <code>dataFormattata</code>.
     * @return String dataFormattata.
     */
    public String getDataFormattata() { return dataFormattata; }
    /**
     * Override del metodo <code>toString</code>.
     * @return String contenente la data nel suo formato "Epoch" e la data nel suo formato "leggibile".
     */
    @Override
    public String toString() {
        return "Data{" +
                "dataEpoch=" + dataEpoch +
                ", dataFormattata='" + dataFormattata + '\'' +
                '}';
    }
}
