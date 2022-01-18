package it.univpm.models;

import java.util.Objects;
import java.util.Vector;

/**
 * <b>Classe</b> contenente i parametri che caratterizzano una citta'.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Citta {

    private long id;
    private String nome;
    private String nazione;
    private Coordinate coord;
    private Vector<Pressione> pressioni;
    private Vector<Temperatura> temperature;
    private Data primaOccorrenzaValori;
    private Data ultimaOccorrenzaValori;

    /**
     * <b>Costruttore</b> della classe <code>Citta</code>.
     *
     * @param id      <b>Codice identificativo</b> della citta'.
     * @param nome    <b>Nome</b> della citta'.
     * @param nazione <b>Nazione</b> in cui la citta' si trova.
     * @param coord   <b>Coordinate</b> della citta' date da longitudine e latitudine.
     */
    public Citta(long id, String nome, String nazione, Coordinate coord, Vector<Pressione> pressioni, Vector<Temperatura> temperature) {
        this.id = id;
        this.nome = nome;
        this.nazione = nazione;
        this.coord = coord;
        this.pressioni = pressioni;
        this.temperature = temperature;
        this.primaOccorrenzaValori = temperature.get(0).getData();
        this.ultimaOccorrenzaValori = temperature.get(temperature.size() - 1).getData();
    }

    /**
     * <b>Costruttore</b> della classe <code>Citta</code> necessario per i test.
     *
     * @param nome                   <b>Nome</b> della citta'.
     * @param nazione                <b>Nazione</b> in cui la citta' si trova.
     * @param primaOccorrenzaValori  Prima data.
     * @param ultimaOccorrenzaValori Ultima data.
     */
    public Citta(String nome, String nazione, Data primaOccorrenzaValori, Data ultimaOccorrenzaValori) {
        this.nome = nome;
        this.nazione = nazione;
        this.primaOccorrenzaValori = primaOccorrenzaValori;
        this.ultimaOccorrenzaValori = ultimaOccorrenzaValori;
    }

    /**
     * <b>Getter</b> dell'attributo <code>id</code>.
     *
     * @return long id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>Setter</b> dell'attributo <code>id</code>
     *
     * @param id Codice identificativo della citta'.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nome</code>.
     *
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * <b>Setter</b> dell'attributo <code>nome</code>
     *
     * @param nome Nome della citta'.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nazione</code>.
     *
     * @return String nazione
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * <b>Setter</b> dell'attributo <code>nazione</code>
     *
     * @param nazione Nazione in cui la citta' si trova.
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * <b>Getter</b> dell'attributo <code>coord</code>.
     *
     * @return Coordinate coord
     */
    public Coordinate getCoord() {
        return coord;
    }

    /**
     * <b>Setter</b> dell'attributo <code>coord</code>
     *
     * @param coord Coordinate della citta' date da longitudine e latitudine.
     */
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    /**
     * <b>Getter</b> dell'attributo <code>pressioni</code>.
     *
     * @return Vector<Pressione> pressioni
     */
    public Vector<Pressione> getPressioni() {
        return pressioni;
    }

    /**
     * <b>Setter</b> dell'attributo <code>pressioni</code>
     *
     * @param pressioni Vettore contenente i dati relativi alle pressioni.
     */
    public void setPressioni(Vector<Pressione> pressioni) {
        this.pressioni = pressioni;
    }

    /**
     * <b>Getter</b> dell'attributo <code>temperature</code>.
     *
     * @return Vector<Temperatura> temperature
     */
    public Vector<Temperatura> getTemperatura() {
        return temperature;
    }

    /**
     * <b>Setter</b> dell'attributo <code>temperature</code>
     *
     * @param temperature Vettore contenente i dati relativi alle temperature.
     */
    public void setTemperatura(Vector<Temperatura> temperature) {
        this.temperature = temperature;
    }

    /**
     * <b>Getter</b> dell'attributo <code>primaOccorrenzaValori</code>.
     *
     * @return primaOccorrenzaValori
     */
    public Data getPrimaOccorrenzaValori() {
        return this.primaOccorrenzaValori;
    }

    /**
     * <b>Setter</b> dell'attributo <code>primaOccorrenzaValori</code>.
     *
     * @param primaOccorrenzaValori prima data per la quale sono disponibili valori.
     */
    public void setPrimaOccorrenzaValori(Data primaOccorrenzaValori) {
        this.primaOccorrenzaValori = primaOccorrenzaValori;
    }

    /**
     * <b>Getter</b> dell'attributo <code>ultimaOccorrenzaValori</code>.
     *
     * @return ultimaOccorrenzaValori
     */
    public Data getUltimaOccorrenzaValori() {
        return this.ultimaOccorrenzaValori;
    }

    /**
     * <b>Setter</b> dell'attributo <code>ultimaOccorrenzaValori</code>.
     *
     * @param ultimaOccorrenzaValori ultima data per la quale sono disponibili valori.
     */
    public void setUltimaOccorrenzaValori(Data ultimaOccorrenzaValori) {
        this.ultimaOccorrenzaValori = ultimaOccorrenzaValori;
    }
}
