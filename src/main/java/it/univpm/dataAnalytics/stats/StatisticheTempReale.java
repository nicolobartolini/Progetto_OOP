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

    @Override
    public double getMinimo (){
        double minimo = arrayTempReale[0];
        for (int i = 1; i < arrayTempReale.length; i++){
            if (arrayTempReale[i] < minimo)
                minimo = arrayTempReale[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo (){
        double massimo = arrayTempReale[0];
        for (int i = 1; i < arrayTempReale.length; i++){
            if (arrayTempReale[i] > massimo)
                massimo = arrayTempReale[i];
        }
        return massimo;
    }

    @Override
    public double getMedia (){
        double somma = 0;
        for (double valore : arrayTempReale)
            somma += valore;
        return somma/((double) arrayTempReale.length);
    }

    @Override
    public double getVarianza (){
        double scartoQuadratico = 0;
        for (double v : arrayTempReale)
            scartoQuadratico += Math.pow((v - getMedia()), 2);
        return scartoQuadratico/((double) arrayTempReale.length);
    }




}
