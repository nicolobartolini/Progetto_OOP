package it.univpm.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * <b>Classe</b> contenente una data, sia nel suo formato "Epoch" che nel suo formato di stringa "leggibile".
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Data {

    private long dataEpoch;
    private LocalDateTime date;
    private String dataFormattata;

    /**
     * <b>Costruttore</b> della classe <code>data</code>.
     * @param dataEpoch data nel formato "Epoch".
     */
    public Data(long dataEpoch) {
        this.dataEpoch = dataEpoch;
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(dataEpoch), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");
        this.dataFormattata = date.format(formatter);
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
    public LocalDateTime getDate() {
        return date;
    }
    /**
     * <b>Setter</b> dell'attributo <code>data</code>.
     *
     * @param date data (istanza della classe Date di Java).
     */
    public void setDate(LocalDateTime date) {
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
