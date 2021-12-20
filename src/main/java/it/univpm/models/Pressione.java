package it.univpm.models;

/**
 * <b>Classe</b> contenente il valore previsto della pressione nella corrispondente data.
 *
 * @author riccardopostacchini
 * @author nicolobartolini
 */
public class Pressione {

    private int valore;
    private Data data;

    /**
     * <b>Costruttore</b> della classe <code>Pressione</code>.
     * @param valore Valore della pressione.
     * @param dataEpoch Data nel formato "Epoch".
     */
    public Pressione(int valore, long dataEpoch) {
        this.valore = valore;
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
     * Override del metodo <code>toString</code>.
     * @return Stringa contenente il valore della pressione alla corrispondente data.
     */
    @Override
    public String toString() {
        return "Pressione{" +
                "valore=" + valore +
                ", data=" + data +
                '}';
    }

    //TODO equals
}
