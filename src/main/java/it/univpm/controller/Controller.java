package it.univpm.controller;

import it.univpm.services.ChiamataService;
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
}
