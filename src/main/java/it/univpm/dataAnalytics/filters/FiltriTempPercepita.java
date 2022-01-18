package it.univpm.dataAnalytics.filters;

import it.univpm.dataAnalytics.stats.StatisticheTempPercepita;
import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.exceptions.InvalidPeriodException;
import it.univpm.models.Citta;
import it.univpm.models.Temperatura;

import java.time.LocalDateTime;
import java.util.Vector;

/**
 * <b>Classe</b> figlia di <code>FiltriTemp</code> e che implementa <code>FiltriInterface</code>. Si occupa di filtrare le statistiche della temperatura percepita.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class FiltriTempPercepita extends FiltriTemp implements FiltriInterface {

    /**
     * <b>Costruttore</b> della classe. Richiama il costruttore della superclasse.
     *
     * @param citta Citta' contenente i valori che saranno necessari ai vari filtri.
     */
    public FiltriTempPercepita(Citta citta) {
        super(citta);
    }

    @Override
    public double getMinimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMinimo();
    }

    @Override
    public double getMassimoGiornaliero(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMassimo();
    }

    @Override
    public double getMediaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getMedia();
    }

    @Override
    public double getVarianzaGiornaliera(LocalDateTime giorno) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheGiornaliere(giorno);
        return stat.getVarianza();
    }

    @Override
    public double getMinimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMinimo();
    }

    @Override
    public double getMassimoFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMassimo();
    }

    @Override
    public double getMediaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getMedia();
    }

    @Override
    public double getVarianzaFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        StatisticheTempPercepita stat = getStatisticheFasciaOraria(oraIniziale, oraFinale);
        return stat.getVarianza();
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempPercepita</code> un vettore di temperature filtrato, relativo solo a un giorno.
     *
     * @param giorno Giorno richiesto.
     * @return <code>StatisticheTempPercepita</code> - Istanza della classe di statistica relativa alla temperatura percepita con riferimento al vettore di temperature filtrato.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    private StatisticheTempPercepita getStatisticheGiornaliere(LocalDateTime giorno) throws InvalidPeriodException {
        if (!isGiornoValido(giorno)) {
            throw new InvalidPeriodException();

        }
        Vector<Temperatura> vTempPercepita = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == giorno.getDayOfMonth())
                vTempPercepita.add(t);
        }
        return new StatisticheTempPercepita(vTempPercepita);
    }

    /**
     * <b>Metodo</b> che fornisce alla classe <code>StatisticheTempPercepita</code> un vettore di temperature filtrato, relativo solo a una fascia oraria.
     *
     * @param oraIniziale Ora iniziale della fascia oraria richiesta.
     * @param oraFinale   Ora finale della fascia oraria richiesta.
     * @return <code>StatisticheTempPercepita</code> - Istanza della classe di statistica relativa alla temperatura percepita con riferimento al vettore di temperature filtrato.
     * @throws InvalidPeriodException Eccezione relativa a un errore nel periodo immesso.
     */
    private StatisticheTempPercepita getStatisticheFasciaOraria(LocalDateTime oraIniziale, LocalDateTime oraFinale) throws InvalidPeriodException {
        if (!isFasciaOrariaValida(oraIniziale, oraFinale)) {
            throw new InvalidPeriodException();

        }
        Vector<Temperatura> vTempPercepita = new Vector<>();
        for (Temperatura t : citta.getTemperatura()) {
            if (t.getData().getDate().getDayOfMonth() == oraIniziale.getDayOfMonth() && t.getData().getDate().getDayOfMonth() == oraFinale.getDayOfMonth()) {
                if (t.getData().getDate().isAfter(oraIniziale) && t.getData().getDate().isBefore(oraFinale))
                    vTempPercepita.add(t);
            }
        }
        return new StatisticheTempPercepita(vTempPercepita);
    }
}
