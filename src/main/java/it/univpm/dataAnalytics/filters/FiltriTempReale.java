package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.models.Citta;
import it.univpm.models.Data;
import it.univpm.models.Temperatura;

import java.time.LocalDateTime;
import java.util.Vector;

/**
 * <b>Classe</b> figlia di <code>FiltriTemp</code> e che implementa <code>FiltriInterface</code>. Si occupa di filtrare le statistiche della temperatura reale.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class FiltriTempReale extends FiltriTemp implements FiltriInterface{

    /**
     * <b>Costruttore</b> della classe. Richiama il costruttore della superclasse.
     * @param citta Citta' contenente i valori che saranno necessari ai vari filtri.
     */
    public FiltriTempReale(Citta citta) {
        super(citta);
    }

    public double getMinimoGiornaliero(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    public double getMassimoGiornaliero(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    public double getMediaGiornaliera(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    public double getVarianzaGiornaliera(LocalDateTime giorno){
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    public double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    public double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    public double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    public double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale){
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempReale</code> un vettore di temperature filtrato, relativo solo a un giorno.
     * @param giorno Giorno richiesto.
     * @return <code>StatisticheTempReale</code> - Istanza della classe di statistica relativa alla temperatura reale con riferimento al vettore di temperature filtrato.
     */
    private StatisticheTempReale getStatisticheGiornaliere(LocalDateTime giorno) {
        if(!isGiornoValido(giorno)){
            //TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for(Temperatura t : citta.getTemperatura()){
            if (t.getData().getDate().getDayOfMonth() == giorno.getDayOfMonth())
                vTempReale.add(t);
        }
        return new StatisticheTempReale(vTempReale);
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempReale</code> un vettore di temperature filtrato, relativo solo a una fascia oraria.
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale Ora finale della fascia oraria richiesta.
     * @return <code>StatisticheTempReale</code> - Istanza della classe di statistica relativa alla temperatura reale con riferimento al vettore di temperature filtrato.
     */
    private StatisticheTempReale getStatisticheFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            // TODO eccezione
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == oraIniziale.getDayOfMonth() && t.getData().getDate().getDayOfMonth() == oraFinale.getDayOfMonth()) {
                if (t.getData().getDate().isAfter(oraIniziale) && t.getData().getDate().isBefore(oraFinale))
                    vTempReale.add(t);
            }
        }
        return new StatisticheTempReale(vTempReale);
    }
}
