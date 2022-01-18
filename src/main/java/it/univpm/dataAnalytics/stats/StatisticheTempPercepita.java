package it.univpm.dataAnalytics.stats;

import it.univpm.models.Temperatura;

import java.util.Vector;

/**
 * <b>Classe</b> che implementa <code>StatisticheInterface</code>. Si occupa di calcolare i valori statistici (minimo, massimo, media e varianza) di una serie di valori relativi alla temperatura <i>percepita</i>.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class StatisticheTempPercepita implements StatisticheInterface {

    private double[] arrayTempPercepita;

    /**
     * <b>Costruttore</b> della classe. Si occupa anche di estrarre i semplici valori numerici delle temperature percepite dal vettore di temperature preso come parametro e trasferirli nell'attributo della classe <code>arrayTempPercepita</code>.
     *
     * @param vTempPercepita Vettore contenente i valori delle temperature.
     */
    public StatisticheTempPercepita(Vector<Temperatura> vTempPercepita) {
        arrayTempPercepita = new double[vTempPercepita.size()];
        for (int i = 0; i < vTempPercepita.size(); i++)
            arrayTempPercepita[i] = vTempPercepita.get(i).getValorePercepito();
    }

    @Override
    public double getMinimo() {
        double minimo;
        try {
            minimo = arrayTempPercepita[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < arrayTempPercepita.length; i++) {
            if (arrayTempPercepita[i] < minimo)
                minimo = arrayTempPercepita[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo() {
        double massimo;
        try {
            massimo = arrayTempPercepita[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < arrayTempPercepita.length; i++) {
            if (arrayTempPercepita[i] > massimo)
                massimo = arrayTempPercepita[i];
        }
        return massimo;
    }

    @Override
    public double getMedia() {
        double somma = 0;
        try {
            for (double valore : arrayTempPercepita)
                somma += valore;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return Math.round((somma / ((double) arrayTempPercepita.length)) * 100.0) / 100.0;
    }

    @Override
    public double getVarianza() {
        double scartoQuadratico = 0;
        try {
            for (double v : arrayTempPercepita)
                scartoQuadratico += Math.pow((v - getMedia()), 2);
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return Math.round((scartoQuadratico / ((double) arrayTempPercepita.length)) * 100.0) / 100.0;
    }
}
