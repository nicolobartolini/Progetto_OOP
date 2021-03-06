package it.univpm.models;

/**
 * <b>Classe</b> contenente il valore previsto della pressione nella corrispondente data.
 */
public class Temperatura {

    private double valoreReale;
    private double valorePercepito;
    private double valoreMinimo;
    private double valoreMassimo;
    private Data data;

    /**
     * <b>Costruttore</b> della classe <code>Temperatura</code>.
     *
     * @param valoreReale     Temperatura reale.
     * @param valorePercepito Temperatura percepita.
     * @param valoreMinimo    Temperatura minima.
     * @param valoreMassimo   Temperatura massima.
     * @param dataEpoch       Data nel formato "Epoch".
     */
    public Temperatura(double valoreReale, double valorePercepito, double valoreMinimo, double valoreMassimo, long dataEpoch) {
        this.valoreReale = valoreReale;
        this.valorePercepito = valorePercepito;
        this.valoreMinimo = valoreMinimo;
        this.valoreMassimo = valoreMassimo;
        this.data = new Data(dataEpoch);
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreReale</code>.
     *
     * @return int valoreReale.
     */
    public double getValoreReale() {
        return valoreReale;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valoreReale</code>.
     *
     * @param valoreReale Temperatura reale.
     */
    public void setValoreReale(double valoreReale) {
        this.valoreReale = valoreReale;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valorePercepito</code>.
     *
     * @return int valorePercepito.
     */
    public double getValorePercepito() {
        return valorePercepito;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valorePercepito</code>.
     *
     * @param valorePercepito Temperatura percepita.
     */
    public void setValorePercepito(double valorePercepito) {
        this.valorePercepito = valorePercepito;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreMinimo</code>.
     *
     * @return int valoreMinimo.
     */
    public double getValoreMinimo() {
        return valoreMinimo;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valoreMinimo</code>.
     *
     * @param valoreMinimo Temperatura minima.
     */
    public void setValoreMinimo(double valoreMinimo) {
        this.valoreMinimo = valoreMinimo;
    }

    /**
     * <b>Getter</b> dell'attributo <code>valoreMassimo</code>.
     *
     * @return int valoreMassimo.
     */
    public double getValoreMassimo() {
        return valoreMassimo;
    }

    /**
     * <b>Setter</b> dell'attributo <code>valoreMassimo</code>.
     *
     * @param valoreMassimo Temperatura massima.
     */
    public void setValoreMassimo(double valoreMassimo) {
        this.valoreMassimo = valoreMassimo;
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
