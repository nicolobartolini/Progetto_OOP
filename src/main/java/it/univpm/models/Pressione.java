package it.univpm.models;

/**
 * <b>Classe</b> contenente il valore previsto della pressione nella corrispondente data.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Pressione {

    private int valore;
    private int valoreMare;
    private int valoreSuolo;
    private Data data;

    /**
     * <b>Costruttore</b> della classe <code>Pressione</code>.
     * @param valore Valore della pressione.
     * @param valoreMare Valore della pressione sul livello del mare.
     * @param valoreSuolo Valore della pressione sul livello del suolo.
     * @param dataEpoch Data nel formato "Epoch".
     *
     */
    public Pressione(int valore, int valoreMare, int valoreSuolo, long dataEpoch) {
        this.valore = valore;
        this.valoreMare = valoreMare;
        this.valoreSuolo = valoreSuolo;
        this.data = new Data(dataEpoch);
    }

    /**
     * <b>Getter</b> dell'attributo <code>valore</code>.
     * @return int valore.
     */
    public int getValore() {
        return valore;
    }
    /**
     * <b>Setter</b> dell'attributo <code>valore</code>.
     * @param valore Valore della pressione.
     */
    public void setValore(int valore) {
        this.valore = valore;
    }
    /**
     * <b>Getter</b> dell'attributo <code>valoreMare</code>.
     * @return int valoreMare.
     */
    public int getValoreMare() {
        return valoreMare;
    }
    /**
     * <b>Setter</b> dell'attributo <code>valoreMare</code>.
     * @param valoreMare Valore della pressione sul livello del mare.
     */
    public void setValoreMare(int valoreMare) {
        this.valoreMare = valoreMare;
    }
    /**
     * <b>Getter</b> dell'attributo <code>valoreSuolo</code>.
     * @return int valoreSuolo.
     */
    public int getValoreSuolo() {
        return valoreSuolo;
    }
    /**
     * <b>Setter</b> dell'attributo <code>valoreSuolo</code>.
     * @param valoreSuolo Valore della pressione sul livello del suolo.
     */
    public void setValoreSuolo(int valoreSuolo) {
        this.valoreSuolo = valoreSuolo;
    }

    /**
     * Override del metodo <code>toString</code>.
     * @return Stringa contenente il valore della pressione, della pressione sul livello del mare e della pressione sul livello del suolo alla corrispondente data.
     */

    @Override
    public String toString() {
        return "Pressione{" +
                "valore=" + valore +
                "valoreMare=" + valoreMare +
                "valoreSuolo=" + valoreSuolo+
                ", data=" + data +
                '}';
    }

    //TODO equals
}
