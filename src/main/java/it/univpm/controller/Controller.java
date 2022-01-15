package it.univpm.controller;

import it.univpm.services.ChiamataService;
import it.univpm.utils.GestioneFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        JSONObject ritorno = service.elaboraChiamata();
        ritorno.remove("temperature");
        return ritorno;

    }

    @GetMapping(value = "/stampaFileJSON")
    @ResponseBody
    public String stampaFileJSON() throws IOException, ParseException {
        ChiamataService service = new ChiamataService("Pescara", "IT");
        GestioneFile.aggiornaFileJSON("Pescara", "IT", service.elaboraChiamata());
        return "Il file è stato salvato in: " + GestioneFile.creaPercorso("Pescara", "It", "json");
    }


}
