package it.univpm.dataAnalytics.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * <b>Classe</b> che implementa <code>StatisticheInterface</code>. Si occupa di calcolare i valori statistici (minimo, massimo, media e varianza) di una serie di valori relativi alla pressione.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class StatisticheStoricoPressione implements StatisticheInterface {

    private long[] pressioni;

    /**
     * <b>Costruttore</b> della classe. Si occupa anche di estrarre i semplici valori numerici delle pressioni dal JSONArray preso come parametro e trasferirli nell'attributo della classe <code>pressioni</code>.
     *
     * @param storico JSONArray contenente lo storico dei valori della pressione accumulati.
     */
    public StatisticheStoricoPressione(JSONArray storico) {
        this.pressioni = new long[storico.size()];
        for (int i = 0; i < storico.size(); i++) {
            pressioni[i] = (long) ((JSONObject) storico.get(i)).get("pressione");
        }
    }

    @Override
    public double getMinimo() {
        double minimo;
        try {
            minimo = pressioni[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < pressioni.length; i++) {
            if (pressioni[i] < minimo)
                minimo = pressioni[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo() {
        double massimo;
        try {
            massimo = pressioni[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < pressioni.length; i++) {
            if (pressioni[i] > massimo)
                massimo = pressioni[i];
        }
        return massimo;
    }

    @Override
    public double getMedia() {
        double somma = 0;
        try {
            for (double valore : pressioni)
                somma += valore;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return somma / ((double) pressioni.length);
    }

    @Override
    public double getVarianza() {
        double scartoQuadratico = 0;
        try {
            for (double v : pressioni)
                scartoQuadratico += Math.pow((v - getMedia()), 2);
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return scartoQuadratico / ((double) pressioni.length);
    }
}
