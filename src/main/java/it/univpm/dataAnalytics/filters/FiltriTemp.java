package it.univpm.dataAnalytics.filters;

import it.univpm.models.Citta;

import java.time.LocalDateTime;

/**
 * <b>Classe</b> contenente un'istanza della classe <code>Citta</code>.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class FiltriTemp {

    /**
     * Attributo <code>Citta</code> utilizzato nelle sottoclassi.
     */
    protected Citta citta;


    /**
     * <b>Costruttore</b> della classe.
     *
     * @param citta Citta' contenente i valori che saranno necessari ai vari filtri.
     */
    public FiltriTemp(Citta citta) {
        this.citta = citta;
    }

    /**
     * <b>Metodo</b> che controlla la validità di un giorno. Un giorno si intende valido se si trova all'interno dell'intervallo di previsioni fornito da OpenWeather (5 giorni).
     *
     * @param giorno Giorno da verificare.
     * @return <code>boolean</code> - <code>true</code> se il giorno è valido, <code>false</code> altrimenti.
     */
    protected boolean isGiornoValido(LocalDateTime giorno) {
        return !giorno.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !giorno.isAfter(citta.getUltimaOccorrenzaValori().getDate());
    }

    /**
     * <b>Metodo</b> che controlla la validità di una fascia oraria. Una fascia oraria si intende valida se si trova all'interno dell'intervallo di previsioni fornito da OpenWeather (5 giorni), se la corrispondente ora iniziale viene prima dell'ora iniziale e se le ore iniziali e finali appartengono allo stesso giorno.
     *
     * @param oraIniziale Ora iniziale della fascia oraria.
     * @param oraFinale   Ora finale della fascia oraria.
     * @return <code>boolean</code> - <code>true</code> se la fascia oraria è valida, <code>false</code> altrimenti.
     */
    protected boolean isFasciaOrariaValida(LocalDateTime oraIniziale, LocalDateTime oraFinale) {
        return (!oraIniziale.isBefore(citta.getPrimaOccorrenzaValori().getDate())
                && !oraFinale.isAfter(citta.getUltimaOccorrenzaValori().getDate()))
                && !oraFinale.isBefore(oraIniziale)
                && !oraIniziale.equals(oraFinale)
                && oraIniziale.getDayOfMonth() == oraFinale.getDayOfMonth();
    }
}
