package it.univpm.dataAnalytics.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StatisticheStoricoPressione implements StatisticheInterface{

    private long[] pressioni;

    public StatisticheStoricoPressione(JSONArray storico) {
        this.pressioni = new long[storico.size()];
        for (int i = 0; i < storico.size(); i++) {
            pressioni[i] = (long) ((JSONObject) storico.get(i)).get("pressione");
        }
    }

    @Override
    public double getMinimo() {
        double minimo = pressioni[0];
        for (int i = 1; i < pressioni.length; i++){
            if (pressioni[i] < minimo)
                minimo = pressioni[i];
        }
        return minimo;
    }

    @Override
    public double getMassimo() {
        double massimo = pressioni[0];
        for (int i = 1; i < pressioni.length; i++){
            if (pressioni[i] > massimo)
                massimo = pressioni[i];
        }
        return massimo;
    }

    @Override
    public double getMedia() {
        double somma = 0;
        for (double valore : pressioni)
            somma += valore;
        return somma/((double) pressioni.length);
    }

    @Override
    public double getVarianza() {
        double scartoQuadratico = 0;
        for (double v : pressioni)
            scartoQuadratico += Math.pow((v - getMedia()), 2);
        return scartoQuadratico/((double) pressioni.length);
    }
}
