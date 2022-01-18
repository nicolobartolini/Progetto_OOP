package it.univpm.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
     * <b>Costruttore</b> della classe <code>data</code>. Si occupa anche di trasformare la data in formato "Epoch" in una <code>LocalDateTime</code> di Java e di formattare tale data in una <code>String</code>.
     * @param dataEpoch data nel formato "Epoch".
     */
    public Data(long dataEpoch) {
        this.dataEpoch = dataEpoch;
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(dataEpoch), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");
        this.dataFormattata = date.format(formatter);
    }

    /**
     * <b>Costruttore</b> della classe <code>Data</code> necessario per i test.
     * @param data Data in <code>LocalDateTime</code>.
     */
    public Data(LocalDateTime data) {
        this.date = data;
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
     * @return <code>LocalDateTime</code> data.
     */
    public LocalDateTime getDate() {
        return date;
    }
    /**
     * <b>Setter</b> dell'attributo <code>data</code>.
     *
     * @param date data (istanza della classe <code>LocalDateTime</code> di Java).
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    /**
     * <b>Getter</b> dell'attributo <code>dataFormattata</code>.
     * @return String dataFormattata.
     */
    public String getDataFormattata() { return dataFormattata; }
}
