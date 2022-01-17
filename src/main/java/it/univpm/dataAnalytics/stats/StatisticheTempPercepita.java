package it.univpm.dataAnalytics.stats;

import it.univpm.models.Temperatura;

import java.util.Vector;

public class StatisticheTempPercepita implements StatisticheInterface{

    private double[] arrayTempPercepita;

    public StatisticheTempPercepita (Vector<Temperatura> vTempPercepita){
        arrayTempPercepita = new double[vTempPercepita.size()];
        for (int i = 0; i < vTempPercepita.size(); i++)
            arrayTempPercepita[i] = vTempPercepita.get(i).getValorePercepito();
    }

    @Override
    public double getMinimo (){
        double minimo = arrayTempPercepita[0];
        for (int i = 1; i < arrayTempPercepita.length; i++){
            if (arrayTempPercepita[i] < minimo)
                minimo = arrayTempPercepita[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo (){
        double massimo = arrayTempPercepita[0];
        for (int i = 1; i < arrayTempPercepita.length; i++){
            if (arrayTempPercepita[i] > massimo)
                massimo = arrayTempPercepita[i];
        }
        return massimo;
    }

    @Override
    public double getMedia (){
        double somma = 0;
        for (double valore : arrayTempPercepita)
            somma += valore;
        return somma/((double) arrayTempPercepita.length);
    }

    @Override
    public double getVarianza (){
        double scartoQuadratico = 0;
        for (double v : arrayTempPercepita)
            scartoQuadratico += Math.pow((v - getMedia()), 2);
        return scartoQuadratico/((double) arrayTempPercepita.length);
    }
}
