package it.univpm.models;

import java.util.Objects;
import java.util.Vector;

/**
 * <b>Classe</b> che contiene i parametri che caratterizzano una <i>citta'</i>.
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

    /**
     * <b>Costruttore</b> della classe <code>Citta</code>.
     *
     * @param id Codice identificativo della citta'.
     * @param nome Nome della citta'.
     * @param nazione Nazione in cui la citta' si trova.
     * @param coord Coordinate della citta' date da longitudine e latitudine.
     *
     */
    public Citta(long id, String nome, String nazione, Coordinate coord, Vector<Pressione> pressioni, Vector<Temperatura> temperature){
        this.id = id;
        this.nome = nome;
        this.nazione = nazione;
        this.coord = coord;
        this.pressioni = pressioni;
        this.temperature = temperature;
    }

    /**
     * <b>Getter</b> dell'attributo <code>id</code>.
     * @return long id
     */
    public long getId() {
        return id;
    }
    /**
     * <b>Setter</b> dell'attributo <code>id</code>
     * @param id Codice identificativo della citta'.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nome</code>.
     * @return String nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * <b>Setter</b> dell'attributo <code>nome</code>
     * @param nome Nome della citta'.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * <b>Getter</b> dell'attributo <code>nazione</code>.
     * @return String nazione
     */
    public String getNazione() {
        return nazione;
    }
    /**
     * <b>Setter</b> dell'attributo <code>nazione</code>
     * @param nazione Nazione in cui la citta' si trova.
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * <b>Getter</b> dell'attributo <code>coord</code>.
     * @return Coordinate coord
     */
    public Coordinate getCoord() {
        return coord;
    }
    /**
     * <b>Setter</b> dell'attributo <code>coord</code>
     * @param coord Coordinate della citta' date da longitudine e latitudine.
     */
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    /**
     * <b>Getter</b> dell'attributo <code>pressioni</code>.
     * @return Vector<Pressione> pressioni
     */
    public Vector<Pressione> getPressioni() {
        return pressioni;
    }
    /**
     * <b>Setter</b> dell'attributo <code>pressioni</code>
     * @param pressioni Vettore contenente i dati relativi alle pressioni.
     */
    public void setPressioni(Vector<Pressione> pressioni) {
        this.pressioni = pressioni;
    }

    /**
     * <b>Getter</b> dell'attributo <code>temperature</code>.
     * @return Vector<Temperatura> temperature
     */
    public Vector<Temperatura> getTemperatura() {
        return temperature;
    }
    /**
     * <b>Setter</b> dell'attributo <code>temperature</code>
     * @param temperature Vettore contenente i dati relativi alle temperature.
     */
    public void setTemperatura(Vector<Temperatura> temperature) {
        this.temperature = temperature;
    }

    /**
     * Override del metodo toString.
     * @return String rappresentante i dati della citta'.
     */
    @Override
    public String toString() {
        return "Citta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", coord=" + coord +
                ", nazione='" + nazione + '\'' +
                ", pressioni=" + pressioni +
                ", temperatura=" + temperature +
                '}';
    }
/*
    @Override
    public boolean equals(Object o) {
        //TODO equals
        return true;
    }

 */
}
