package it.univpm.dataAnalytics.stats;

import it.univpm.models.Temperatura;

import java.util.Vector;

/**
 * <b>Classe</b> che implementa <code>StatisticheInterface</code>. Si occupa di calcolare i valori statistici (minimo, massimo, media e varianza) di una serie di valori relativi alla temperatura <i>reale</i>.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class StatisticheTempReale implements StatisticheInterface{

    private double[] arrayTempReale;

    /**
     * <b>Costruttore</b> della classe. Si occupa anche di estrarre i semplici valori numerici delle temperature reali dal vettore di temperature preso come parametro e trasferirli nell'attributo della classe <code>arrayTempReale</code>.
     *
     * @param vTempReale Vettore contenente i valori delle temperature.
     */
    public StatisticheTempReale (Vector<Temperatura> vTempReale){
        arrayTempReale = new double[vTempReale.size()];
        for (int i = 0; i < vTempReale.size(); i++)
            arrayTempReale[i] = vTempReale.get(i).getValoreReale();
    }

    /**
     * <b>Costruttore</b> della classe <code>StatisticheTempReale</code> necessario per i test.
     * @param arrayTempReale Vettore di <code>double</code>.
     */
    public StatisticheTempReale (double[] arrayTempReale) {
        this.arrayTempReale = arrayTempReale;
    }

    @Override
    public double getMinimo (){
        double minimo;
        try {
            minimo = arrayTempReale[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < arrayTempReale.length; i++){
            if (arrayTempReale[i] < minimo)
                minimo = arrayTempReale[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo (){
        double massimo;
        try {
            massimo = arrayTempReale[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        for (int i = 1; i < arrayTempReale.length; i++){
            if (arrayTempReale[i] > massimo)
                massimo = arrayTempReale[i];
        }
        return massimo;
    }

    @Override
    public double getMedia (){
        double somma = 0;
        try {
            for (double valore : arrayTempReale)
                somma += valore;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return Math.round((somma/((double) arrayTempReale.length))* 100.0) / 100.0;
    }

    @Override
    public double getVarianza (){
        double scartoQuadratico = 0;
        try {
            for (double v : arrayTempReale)
                scartoQuadratico += Math.pow((v - getMedia()), 2);
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0.0;
        }
        return Math.round((scartoQuadratico/((double) arrayTempReale.length)) * 100.0) / 100.0;
    }
}
