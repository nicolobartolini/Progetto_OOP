package it.univpm.models;

public class Temperatura {

    private int valoreReale;
    private int valorePercepito;
    private Data data;

    /**
     * <b>Costruttore</b> della classe <code>Temperatura</code>.
     * @param valoreReale Temperatura reale.
     * @param valorePercepito Temperatura percepita.
     * @param dataEpoch Data nel formato "Epoch".
     */
    public Temperatura(int valoreReale, int valorePercepito, long dataEpoch) {
        this.valoreReale = valoreReale;
        this.valorePercepito = valorePercepito;
        this.data = new Data(dataEpoch);
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreReale</code>.
     * @return int valoreReale.
     */
    public int getValoreReale() {
        return valoreReale;
    }
    /**
     * <b>Setter</b> dell'attributo <code>valoreReale</code>.
     * @param valoreReale Temperatura reale.
     */
    public void setValoreReale(int valoreReale) {
        this.valoreReale = valoreReale;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valorePercepito</code>.
     * @return int valorePercepito.
     */
    public int getValorePercepito() {
        return valorePercepito;
    }
    /**
     * <b>Setter</b> dell'attributo <code>valorePercepito</code>.
     * @param valorePercepito Temperatura percepita.
     */
    public void setValorePercepito(int valorePercepito) {
        this.valorePercepito = valorePercepito;
    }

    /**
     * Override del metodo <code>toString</code>.
     * @return Stringa contenente le temperature reale e percepita alla corrispondente data.
     */
    @Override
    public String toString() {
        return "Temperatura{" +
                "valoreReale=" + valoreReale +
                ", valorePercepito=" + valorePercepito +
                ", data=" + data +
                '}';
    }

    //TODO equals
}
