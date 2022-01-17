package it.univpm.dataAnalytics.stats;

import it.univpm.models.Temperatura;

import java.util.Vector;

public class StatisticheTempReale implements StatisticheInterface{

    private double[] arrayTempReale;

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
