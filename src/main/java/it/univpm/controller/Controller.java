package it.univpm.controller;

import it.univpm.dataAnalytics.filters.FiltriTempReale;
import it.univpm.models.Citta;
import it.univpm.models.Data;
import it.univpm.services.ChiamataService;
import it.univpm.utils.GestioneFile;
import it.univpm.utils.ParserCitta;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class Controller {

    // ChiamataService service;

    @GetMapping(value = "/getGeneral")
    @ResponseBody
    public JSONObject getAllPressTemp(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                      @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {
        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        return service.elaboraChiamata();
    }

    @GetMapping(value = "/getPressioni")
    @ResponseBody
    public JSONObject getPressioni(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                   @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {

        ChiamataService service = new ChiamataService(nomeCitta, nazione);
        JSONObject risultato = service.elaboraChiamata();
        risultato.remove("temperature");
        return risultato;

    }

    @PostMapping(value = "/stampaFileJSON")
    @ResponseBody
    public String stampaFileJSON(@RequestParam (name = "city", defaultValue = "Ancona") String nomeCitta,
                                 @RequestParam(name = "nation", defaultValue = "IT") String nazione) throws IOException, ParseException {
        GestioneFile.aggiornaFileJSON(nomeCitta, nazione);
        return "Il file è stato salvato in: " + GestioneFile.creaPercorso(nomeCitta, nazione);
    }

    @GetMapping(value = "/filtraTemperatureReali/{year}/{month}/{day}")
    @ResponseBody
    public JSONObject filtraTemperatureReali(@RequestParam(name = "city", defaultValue = "Ancona") String nomeCitta,
                                             @RequestParam(name = "nation", defaultValue = "IT") String nazione, @PathVariable int day, @PathVariable int month, @PathVariable int year) throws IOException, ParseException {
        LocalDateTime giorno = LocalDateTime.of(year, month, day, 0, 0, 0);
        ParserCitta parser = new ParserCitta(nomeCitta, nazione);
        Citta citta = parser.leggiDati();
        FiltriTempReale filtri = new FiltriTempReale(citta);
        JSONObject risultato = new JSONObject();
        JSONObject periodo = new JSONObject();
        periodo.put("year", year);
        periodo.put("month", month);
        periodo.put("day", day);
        risultato.put("periodo_filtro", periodo);
        JSONObject valoriFiltrati = new JSONObject();
        valoriFiltrati.put("minimo", filtri.getMinimoGiornaliero(giorno));
        valoriFiltrati.put("massimo", filtri.getMassimoGiornaliero(giorno));
        valoriFiltrati.put("media", filtri.getMediaGiornaliera(giorno));
        valoriFiltrati.put("varianza", filtri.getVarianzaGiornaliera(giorno));
        risultato.put("risultato_filtraggio", valoriFiltrati);
        return risultato;
    }
}
