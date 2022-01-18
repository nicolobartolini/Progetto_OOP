package it.univpm.models;

/**
 * <b>Classe</b> contenente il valore previsto della pressione nella corrispondente data.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Pressione {

    private long valore;
    private long valoreMare;
    private long valoreSuolo;
    private Data data;

    /**
     * <b>Costruttore</b> della classe <code>Pressione</code>.
     *
     * @param valore      Valore della pressione.
     * @param valoreMare  Valore della pressione sul livello del mare.
     * @param valoreSuolo Valore della pressione sul livello del suolo.
     * @param dataEpoch   Data nel formato "Epoch".
     */
    public Pressione(long valore, long valoreMare, long valoreSuolo, long dataEpoch) {
        this.valore = valore;
        this.valoreMare = valoreMare;
        this.valoreSuolo = valoreSuolo;
        this.data = new Data(dataEpoch);
    }

    /**
     * <b>Getter</b> dell'attributo <code>valore</code>.
     *
     * @return long valore.
     */
    public long getValore() {
        return valore;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valore</code>.
     *
     * @param valore Valore della pressione.
     */
    public void setValore(int valore) {
        this.valore = valore;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreMare</code>.
     *
     * @return long valoreMare.
     */
    public long getValoreMare() {
        return valoreMare;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valoreMare</code>.
     *
     * @param valoreMare Valore della pressione sul livello del mare.
     */
    public void setValoreMare(int valoreMare) {
        this.valoreMare = valoreMare;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreSuolo</code>.
     *
     * @return long valoreSuolo.
     */
    public long getValoreSuolo() {
        return valoreSuolo;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valoreSuolo</code>.
     *
     * @param valoreSuolo Valore della pressione sul livello del suolo.
     */
    public void setValoreSuolo(int valoreSuolo) {
        this.valoreSuolo = valoreSuolo;
    }

    /**
     * <b>Getter</b> dell'attributo <code>data</code>.
     *
     * @return Data data.
     */
    public Data getData() {
        return data;
    }
}
