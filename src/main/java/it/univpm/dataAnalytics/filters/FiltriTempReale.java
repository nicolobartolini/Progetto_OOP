package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempPercepita;
import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.exceptions.InvalidPeriodException;
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
public class FiltriTempReale extends FiltriTemp implements FiltriInterface {

    /**
     * <b>Costruttore</b> della classe. Richiama il costruttore della superclasse.
     *
     * @param citta Citta' contenente i valori che saranno necessari ai vari filtri.
     */
    public FiltriTempReale(Citta citta) {
        super(citta);
    }

    @Override
    public double getMinimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    @Override
    public double getMassimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    @Override
    public double getMediaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    @Override
    public double getVarianzaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    @Override
    public double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    @Override
    public double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    @Override
    public double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    @Override
    public double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempReale stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempReale</code> un vettore di temperature filtrato, relativo solo a un giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>StatisticheTempReale</code> - Istanza della classe di statistica relativa alla temperatura reale con riferimento al vettore di temperature filtrato.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    private StatisticheTempReale getStatisticheGiornaliere(LocalDateTime giorno) throws InvalidPeriodException {
        if (!isGiornoValido(giorno)) {
            throw new InvalidPeriodException();
        }
        Vector<Temperatura> vTempReale = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == giorno.getDayOfMonth())
                vTempReale.add(t);
        }
        return new StatisticheTempReale(vTempReale);
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempReale</code> un vettore di temperature filtrato, relativo solo a una fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>StatisticheTempReale</code> - Istanza della classe di statistica relativa alla temperatura reale con riferimento al vettore di temperature filtrato.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    private StatisticheTempReale getStatisticheFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            throw new InvalidPeriodException();
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
