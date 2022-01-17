package it.univpm.controller;

import it.univpm.dataAnalytics.filters.FiltriTemp;
import it.univpm.dataAnalytics.filters.FiltriTempPercepita;
import it.univpm.dataAnalytics.filters.FiltriTempReale;
import it.univpm.dataAnalytics.stats.StatisticheStoricoPressione;
import it.univpm.dataAnalytics.stats.StatisticheTempPercepita;
import it.univpm.dataAnalytics.stats.StatisticheTempReale;
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

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Vector;

@RestController
public class Controller {

    // ChiamataService service;

    @GetMapping("/getGeneral")
    @ResponseBody
    public JSONObject getAllPressTemp(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                      @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {
        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        return service.elaboraChiamata();
    }

    @GetMapping("/getPressioni")
    @ResponseBody
    public JSONObject getPressioni(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                   @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {

        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        JSONObject risultato = service.elaboraChiamata();
        risultato.remove("temperature");
        return risultato;

    }

    @PostMapping("/stampaFileJSON")
    @ResponseBody
    public String stampaFileJSON(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                 @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {
        GestioneFile.aggiornaFileJSON(nomeCitta, nazione);
        return "Il file è stato salvato in: " + GestioneFile.creaPercorso(nomeCitta, nazione);
    }

    @GetMapping({"/filtriTemperature/{tipoTemp}/{tipoFiltro}/{year}/{month}/{day}/{start_hour}/{end_hour}", "/filtriTemperature/{tipoTemp}/{tipoFiltro}/{year}/{month}/{day}"})
    @ResponseBody
    public JSONObject filtraTemp(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                 @RequestParam(name = "nation", defaultValue = "IT") String nazione,
                                 @PathVariable int day, @PathVariable int month, @PathVariable int year,
                                 @PathVariable Optional<Integer> end_hour, @PathVariable Optional<Integer> start_hour, @PathVariable String tipoFiltro, @PathVariable String tipoTemp) throws IOException, ParseException {
        FiltriTemp filtri;
        int start_hour_value = 0;
        int end_hour_value = 0;
        if (start_hour.isPresent())
            start_hour_value = start_hour.get();
        if (end_hour.isPresent())
            end_hour_value = end_hour.get();
        if (tipoTemp.equalsIgnoreCase("reali") || tipoTemp.equalsIgnoreCase("reale")) {
            if (tipoFiltro.equalsIgnoreCase("giornaliero") || tipoFiltro.equalsIgnoreCase("giorno")) {
                LocalDateTime giorno = LocalDateTime.of(year, month, day, 0, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                Citta citta = parser.leggiDati();
                filtri = new FiltriTempReale(citta);
                JSONObject risultato = new JSONObject();
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
            }
            else if (tipoFiltro.equalsIgnoreCase("fasciaoraria") || tipoFiltro.equalsIgnoreCase("fascia") || tipoFiltro.equalsIgnoreCase("orario")) {
                LocalDateTime oraIniziale = LocalDateTime.of(year, month, day, start_hour_value, 0, 0);
                LocalDateTime oraFinale = LocalDateTime.of(year, month, day, end_hour_value, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                Citta citta = parser.leggiDati();
                filtri = new FiltriTempReale(citta);
                JSONObject risultato = new JSONObject();
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
            }
        }
        else if (tipoTemp.equalsIgnoreCase("percepite") || tipoTemp.equalsIgnoreCase("percepita")) {
            if (tipoFiltro.equalsIgnoreCase("giornaliero") || tipoFiltro.equalsIgnoreCase("giorno")) {
                LocalDateTime giorno = LocalDateTime.of(year, month, day, 0, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                Citta citta = parser.leggiDati();
                filtri = new FiltriTempPercepita(citta);
                JSONObject risultato = new JSONObject();
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
            }
            else if (tipoFiltro.equalsIgnoreCase("fasciaoraria") || tipoFiltro.equalsIgnoreCase("fascia") || tipoFiltro.equalsIgnoreCase("orario")) {
                LocalDateTime oraIniziale = LocalDateTime.of(year, month, day, start_hour_value, 0, 0);
                LocalDateTime oraFinale = LocalDateTime.of(year, month, day, end_hour_value, 0, 0);
                ParserCitta parser = new ParserCitta(nomeCitta, nazione);
                Citta citta = parser.leggiDati();
                filtri = new FiltriTempPercepita(citta);
                JSONObject risultato = new JSONObject();
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
            }
            else ;
                //TODO eccezioni

        }
        return new JSONObject();
    }

    @GetMapping(value = "/stats/{tipoDato}")
    @ResponseBody
    public JSONObject getStatistiche(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                     @RequestParam(name = "nation", defaultValue = "IT") String nazione,
                                     @PathVariable String tipoDato) throws IOException, ParseException {

        if (tipoDato.equalsIgnoreCase("pressione") || tipoDato.equalsIgnoreCase("pressioni")) {
            JSONParser parser = new JSONParser();
            Object o = parser.parse(new FileReader(GestioneFile.creaPercorso(nomeCitta, nazione)));
            JSONArray storico = (JSONArray) o;
            StatisticheStoricoPressione stat = new StatisticheStoricoPressione(storico);
            JSONObject risultato = new JSONObject();
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
        }
        else if (tipoDato.equalsIgnoreCase("temperaturaReale") ||
                 tipoDato.equalsIgnoreCase("temperatureReali")) {
            ParserTemperatura parser = new ParserTemperatura(nomeCitta, nazione);
            Vector<Temperatura> temperature = parser.leggiDati();
            StatisticheTempReale stat = new StatisticheTempReale(temperature);
            JSONObject risultato = new JSONObject();
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
        }
        else if (tipoDato.equalsIgnoreCase("temperaturaPercepita") ||
                tipoDato.equalsIgnoreCase("temperaturePercepite")) {
            ParserTemperatura parser = new ParserTemperatura(nomeCitta, nazione);
            Vector<Temperatura> temperature = parser.leggiDati();
            StatisticheTempPercepita stat = new StatisticheTempPercepita(temperature);
            JSONObject risultato = new JSONObject();
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
        }
        else {
            // TODO eccezioni

        }
        return new JSONObject();
    }
}
