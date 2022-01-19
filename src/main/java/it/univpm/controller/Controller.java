package it.univpm.controller;

import it.univpm.dataAnalytics.filters.FiltriTemp;
import it.univpm.dataAnalytics.filters.FiltriTempPercepita;
import it.univpm.dataAnalytics.filters.FiltriTempReale;
import it.univpm.dataAnalytics.stats.StatisticheStoricoPressione;
import it.univpm.dataAnalytics.stats.StatisticheTempReale;
import it.univpm.exceptions.*;
import it.univpm.models.Citta;
import it.univpm.models.Temperatura;
import it.univpm.services.ChiamataService;
import it.univpm.utils.GestioneFile;
import it.univpm.utils.ParserCitta;
import it.univpm.utils.ParserTemperatura;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Vector;

/**
 * <b>Classe</b> controller che si occupa di gestire le varie rotte dell'applicazione.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
@RestController
public class Controller {

    /**
     * Rotta che, data una citta', restituisce tutte le previsioni relative alle pressioni e alle temperature per i prossimi 5 giorni.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @return <code>JSONObject</code> - JSONObject contenente le informazioni sulle pressioni, sulle temperature e sulla citta'.
     */
    @GetMapping("/getGeneral")
    @ResponseBody
    public JSONObject getAllPressTemp(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                      @RequestParam(name = "nation", defaultValue = "IT") String nazione) {
        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        JSONObject risultato = new JSONObject();
        try {
            risultato = service.elaboraChiamata();
        } catch (FileNotFoundException e) {
            risultato.put("errore", 404);
            risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
            risultato.put("errore_in", "/getGeneral");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return risultato;
    }

    /**
     * Rotta che, data una citta', restituisce tutte le previsioni relative alle pressioni per i prossimi 5 giorni.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @return <code>JSONObject</code> - JSONObject contenente le previsioni sulle pressioni.
     */
    @GetMapping("/getPressioni")
    @ResponseBody
    public JSONObject getPressioni(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                   @RequestParam(name = "nation", defaultValue = "IT") String nazione) {
        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        JSONObject risultato = new JSONObject();
        try {
            risultato = service.elaboraChiamata();
            risultato.remove("temperature");
        } catch (FileNotFoundException e) {
            risultato.put("errore", 404);
            risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
            risultato.put("errore_in", "/getPressioni");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return risultato;
    }

    /**
     * Rotta che esegue il metodo di accumulo dati sulla pressione e restituisce una stringa informativa sul percorso del file salvato.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @return <code>String</code> - Stringa informativa sul percorso del file salvato.
     */
    @GetMapping("/stampaFileJSON")
    @ResponseBody
    public String stampaFileJSON(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                 @RequestParam(name = "nation", defaultValue = "IT") String nazione) {
        try {
            GestioneFile.aggiornaFileJSON(nomeCitta, nazione);
        } catch (FileNotFoundException e) {
            return "Il file non è stato creato dal momento che la città " + nomeCitta + ", " + nazione + " è inesistente";
        }
        return "Il file è stato salvato in: " + GestioneFile.creaPercorso(nomeCitta, nazione);
    }

    /**
     * Rotta che si occupa del filtraggio delle statistiche relative alla temperatura.
     *
     * @param nomeCitta  <b>Nome</b> della citta'.
     * @param nazione    <b>Nazione</b> della citta'.
     * @param day        <b>Giorno</b> del periodo di filtro richiesto.
     * @param month      <b>Mese</b> del periodo di filtro richiesto.
     * @param year       <b>Anno</b> del periodo di filtro richiesto.
     * @param end_hour   <b>Ora finale</b> della fascia oraria richiesta.
     * @param start_hour <b>Ora iniziale</b> della fascia oraria richiesta.
     * @param tipoFiltro Tipo di filtraggio: giornaliero o a fascia oraria.
     * @param tipoTemp   Tipo di temperatura: reale o percepita.
     * @return <code>JSONObject</code> - JSONObject contenente il risultato dei filtri richiesti.
     * @throws IOException                Eccezione relativa all'input/output.
     * @throws ParseException             Eccezione relativa al parsing.
     * @throws InvalidTempTypeException   Eccezione relativa a un errato tipo di temperatura richiesto.
     * @throws InvalidFilterTypeException Eccezione relativa a un errato tipo di filtraggio richiesto.
     * @throws InvalidPeriodException     Eccezione relativa a un errore nel periodo immesso.
     */
    @GetMapping({"/filtriTemperature/{tipoTemp}/{tipoFiltro}/{year}/{month}/{day}/{start_hour}/{end_hour}", "/filtriTemperature/{tipoTemp}/{tipoFiltro}/{year}/{month}/{day}"})
    @ResponseBody
    public JSONObject filtraTemp(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                 @RequestParam(name = "nation", defaultValue = "IT") String nazione,
                                 @PathVariable int day, @PathVariable int month, @PathVariable int year,
                                 @PathVariable Optional<Integer> end_hour, @PathVariable Optional<Integer> start_hour,
                                 @PathVariable String tipoFiltro, @PathVariable String tipoTemp) throws IOException, ParseException, InvalidTempTypeException, InvalidFilterTypeException, InvalidPeriodException {
        FiltriTemp filtri;

        int start_hour_value = 0;
        int end_hour_value = 0;
        if (start_hour.isPresent())
            start_hour_value = start_hour.get();
        if (end_hour.isPresent())
            end_hour_value = end_hour.get();

        if (tipoTemp.equalsIgnoreCase("reale")) {
            if (tipoFiltro.equalsIgnoreCase("giornaliero")) {
                LocalDateTime giorno = LocalDateTime.of(year, month, day, 0, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                JSONObject risultato = new JSONObject();
                Citta citta;
                try {
                    citta = parser.leggiDati();
                } catch (FileNotFoundException e) {
                    risultato.put("errore", 404);
                    risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                    risultato.put("errore_in", "filtriTemperature/reale/giornaliero");
                    return risultato;
                }
                filtri = new FiltriTempReale(citta);
                JSONObject identificatoreCitta = new JSONObject();
                JSONObject valoriFiltrati = new JSONObject();
                JSONObject periodo = new JSONObject();
                identificatoreCitta.put("id", citta.getId());
                identificatoreCitta.put("nome", citta.getNome());
                identificatoreCitta.put("nazione", citta.getNazione());
                periodo.put("year", year);
                periodo.put("month", month);
                periodo.put("day", day);
                valoriFiltrati.put("minimo", ((FiltriTempReale) filtri).getMinimoGiornaliero(giorno));
                valoriFiltrati.put("massimo", ((FiltriTempReale) filtri).getMassimoGiornaliero(giorno));
                valoriFiltrati.put("media", ((FiltriTempReale) filtri).getMediaGiornaliera(giorno));
                valoriFiltrati.put("varianza", ((FiltriTempReale) filtri).getVarianzaGiornaliera(giorno));
                risultato.put("risultato_filtraggio", valoriFiltrati);
                risultato.put("città", identificatoreCitta);
                risultato.put("periodo_filtro", periodo);
                return risultato;
            } else if (tipoFiltro.equalsIgnoreCase("fasciaoraria")) {
                LocalDateTime oraIniziale = LocalDateTime.of(year, month, day, start_hour_value, 0, 0);
                LocalDateTime oraFinale = LocalDateTime.of(year, month, day, end_hour_value, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                JSONObject risultato = new JSONObject();
                Citta citta;
                try {
                    citta = parser.leggiDati();
                } catch (FileNotFoundException e) {
                    risultato.put("errore", 404);
                    risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                    risultato.put("errore_in", "filtriTemperature/reale/fasciaOraria");
                    return risultato;
                }
                filtri = new FiltriTempReale(citta);
                JSONObject periodo = new JSONObject();
                JSONObject fasciaOraria = new JSONObject();
                JSONObject valoriFiltrati = new JSONObject();
                JSONObject identificatoreCitta = new JSONObject();
                identificatoreCitta.put("id", citta.getId());
                identificatoreCitta.put("nome", citta.getNome());
                identificatoreCitta.put("nazione", citta.getNazione());
                fasciaOraria.put("start_hour", start_hour);
                fasciaOraria.put("end_hour", end_hour);
                periodo.put("fascia_oraria", fasciaOraria);
                periodo.put("year", year);
                periodo.put("month", month);
                periodo.put("day", day);
                valoriFiltrati.put("minimo", ((FiltriTempReale) filtri).getMinimoFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("massimo", ((FiltriTempReale) filtri).getMassimoFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("media", ((FiltriTempReale) filtri).getMediaFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("varianza", ((FiltriTempReale) filtri).getVarianzaFasciaOraria(oraIniziale, oraFinale));
                risultato.put("periodo_filtro", periodo);
                risultato.put("città", identificatoreCitta);
                risultato.put("risultato_filtraggio", valoriFiltrati);
                return risultato;
            } else throw new InvalidFilterTypeException();
        } else if (tipoTemp.equalsIgnoreCase("percepita")) {
            if (tipoFiltro.equalsIgnoreCase("giornaliero")) {
                LocalDateTime giorno = LocalDateTime.of(year, month, day, 0, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                JSONObject risultato = new JSONObject();
                Citta citta;
                try {
                    citta = parser.leggiDati();
                } catch (FileNotFoundException e) {
                    risultato.put("errore", 404);
                    risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                    risultato.put("errore_in", "filtriTemperature/percepita/giornaliero");
                    return risultato;
                }
                filtri = new FiltriTempPercepita(citta);
                JSONObject identificatoreCitta = new JSONObject();
                JSONObject valoriFiltrati = new JSONObject();
                JSONObject periodo = new JSONObject();
                identificatoreCitta.put("id", citta.getId());
                identificatoreCitta.put("nome", citta.getNome());
                identificatoreCitta.put("nazione", citta.getNazione());
                periodo.put("year", year);
                periodo.put("month", month);
                periodo.put("day", day);
                valoriFiltrati.put("minimo", ((FiltriTempPercepita) filtri).getMinimoGiornaliero(giorno));
                valoriFiltrati.put("massimo", ((FiltriTempPercepita) filtri).getMassimoGiornaliero(giorno));
                valoriFiltrati.put("media", ((FiltriTempPercepita) filtri).getMediaGiornaliera(giorno));
                valoriFiltrati.put("varianza", ((FiltriTempPercepita) filtri).getVarianzaGiornaliera(giorno));
                risultato.put("risultato_filtraggio", valoriFiltrati);
                risultato.put("città", identificatoreCitta);
                risultato.put("periodo_filtro", periodo);
                return risultato;
            } else if (tipoFiltro.equalsIgnoreCase("fasciaoraria")) {
                LocalDateTime oraIniziale = LocalDateTime.of(year, month, day, start_hour_value, 0, 0);
                LocalDateTime oraFinale = LocalDateTime.of(year, month, day, end_hour_value, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                JSONObject risultato = new JSONObject();
                Citta citta;
                try {
                    citta = parser.leggiDati();
                } catch (FileNotFoundException e) {
                    risultato.put("errore", 404);
                    risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                    risultato.put("errore_in", "filtriTemperature/percepita/fasciaOraria");
                    return risultato;
                }
                filtri = new FiltriTempPercepita(citta);
                JSONObject periodo = new JSONObject();
                JSONObject fasciaOraria = new JSONObject();
                JSONObject valoriFiltrati = new JSONObject();
                JSONObject identificatoreCitta = new JSONObject();
                identificatoreCitta.put("id", citta.getId());
                identificatoreCitta.put("nome", citta.getNome());
                identificatoreCitta.put("nazione", citta.getNazione());
                fasciaOraria.put("start_hour", start_hour);
                fasciaOraria.put("end_hour", end_hour);
                periodo.put("fascia_oraria", fasciaOraria);
                periodo.put("year", year);
                periodo.put("month", month);
                periodo.put("day", day);
                valoriFiltrati.put("minimo", ((FiltriTempPercepita) filtri).getMinimoFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("massimo", ((FiltriTempPercepita) filtri).getMassimoFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("media", ((FiltriTempPercepita) filtri).getMediaFasciaOraria(oraIniziale, oraFinale));
                valoriFiltrati.put("varianza", ((FiltriTempPercepita) filtri).getVarianzaFasciaOraria(oraIniziale, oraFinale));
                risultato.put("periodo_filtro", periodo);
                risultato.put("città", identificatoreCitta);
                risultato.put("risultato_filtraggio", valoriFiltrati);
                return risultato;
            } else throw new InvalidFilterTypeException();
        } else throw new InvalidTempTypeException();
    }

    /**
     * Rotta che si occupa delle statistiche sull'intero intervallo di 5 giorni relative alla temperatura oppure allo storico delle pressioni.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @param tipoDato  Tipo di dato del quale si vogliono ottenere le statistiche: pressione, temperature reale o temperatura percepita.
     * @return <code>JSONObject</code> - JSONObject contenente i dati richiesti.
     * @throws IOException               Eccezione relativa all'input/output.
     * @throws ParseException            Eccezione relativa al parsing.
     * @throws InvalidStatsTypeException Eccezione relativa a un errato tipo di dato richiesto per la statistica.
     */
    @GetMapping(value = "/stats/{tipoDato}")
    @ResponseBody
    public JSONObject getStatistiche(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                     @RequestParam(name = "nation", defaultValue = "IT") String nazione,
                                     @PathVariable String tipoDato) throws IOException, ParseException, InvalidStatsTypeException {
        if (tipoDato.equalsIgnoreCase("pressione")) {
            JSONParser parser = new JSONParser();
            JSONObject risultato = new JSONObject();
            Object o;
            try {
                o = parser.parse(new FileReader(GestioneFile.creaPercorso(nomeCitta, nazione)));
            } catch (FileNotFoundException e) {
                risultato.put("errore", 404);
                risultato.put("descrizione", "[" + GestioneFile.creaPercorso(nomeCitta, nazione) + "] Il file non c'è.");
                risultato.put("errore_in", "stats/pressione");
                return risultato;
            }
            JSONArray storico = (JSONArray) o;
            StatisticheStoricoPressione stat = new StatisticheStoricoPressione(storico);
            JSONObject periodo = new JSONObject();
            JSONObject statistiche = new JSONObject();
            JSONObject identificatoreCitta = new JSONObject();
            periodo.put("start_date", ((JSONObject) storico.get(0)).get("dtFormat"));
            periodo.put("end_date", ((JSONObject) storico.get(storico.size() - 1)).get("dtFormat"));
            statistiche.put("minimo", stat.getMinimo());
            statistiche.put("massimo", stat.getMassimo());
            statistiche.put("media", stat.getMedia());
            statistiche.put("varianza", stat.getVarianza());
            identificatoreCitta.put("nome", nomeCitta);
            identificatoreCitta.put("nazione", nazione);
            risultato.put("periodo", periodo);
            risultato.put("statistiche", statistiche);
            risultato.put("città", identificatoreCitta);
            return risultato;
        } else if (tipoDato.equalsIgnoreCase("temperaturaReale")) {
            ParserTemperatura parser = new ParserTemperatura(nomeCitta, nazione);
            JSONObject risultato = new JSONObject();
            Vector<Temperatura> temperature;
            StatisticheTempReale stat;
            try {
                temperature = parser.leggiDati();
                stat = new StatisticheTempReale(temperature);
            } catch (FileNotFoundException e) {
                risultato.put("errore", 404);
                risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                risultato.put("errore_in", "stats/temperaturaReale");
                return risultato;
            }
            JSONObject periodo = new JSONObject();
            JSONObject statistiche = new JSONObject();
            JSONObject identificatoreCitta = new JSONObject();
            periodo.put("start_date", temperature.get(0).getData().getDataFormattata());
            periodo.put("end_date", temperature.get(temperature.size() - 1).getData().getDataFormattata());
            statistiche.put("minimo", stat.getMinimo());
            statistiche.put("massimo", stat.getMassimo());
            statistiche.put("media", stat.getMedia());
            statistiche.put("varianza", stat.getVarianza());
            identificatoreCitta.put("nome", nomeCitta);
            identificatoreCitta.put("nazione", nazione);
            risultato.put("periodo", periodo);
            risultato.put("statistiche", statistiche);
            risultato.put("città", identificatoreCitta);
            return risultato;
        } else if (tipoDato.equalsIgnoreCase("temperaturaPercepita")) {
            ParserTemperatura parser = new ParserTemperatura(nomeCitta, nazione);
            JSONObject risultato = new JSONObject();
            Vector<Temperatura> temperature;
            StatisticheTempReale stat;
            try {
                temperature = parser.leggiDati();
                stat = new StatisticheTempReale(temperature);
            } catch (FileNotFoundException e) {
                risultato.put("errore", 404);
                risultato.put("descrizione", "[" + nomeCitta + ", " + nazione + "] Città non trovata.");
                risultato.put("errore_in", "stats/temperaturaPercepita");
                return risultato;
            }
            JSONObject periodo = new JSONObject();
            JSONObject statistiche = new JSONObject();
            JSONObject identificatoreCitta = new JSONObject();
            periodo.put("start_date", temperature.get(0).getData().getDataFormattata());
            periodo.put("end_date", temperature.get(temperature.size() - 1).getData().getDataFormattata());
            statistiche.put("minimo", stat.getMinimo());
            statistiche.put("massimo", stat.getMassimo());
            statistiche.put("media", stat.getMedia());
            statistiche.put("varianza", stat.getVarianza());
            identificatoreCitta.put("nome", nomeCitta);
            identificatoreCitta.put("nazione", nazione);
            risultato.put("periodo", periodo);
            risultato.put("statistiche", statistiche);
            risultato.put("città", identificatoreCitta);
            return risultato;
        } else throw new InvalidStatsTypeException();
    }
}
