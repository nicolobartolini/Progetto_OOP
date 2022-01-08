package it.univpm.services;

import it.univpm.models.Citta;
import it.univpm.utils.ParserCitta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ChiamataService {
    private Citta citta;
    private ParserCitta parserCitta;
    //TODO filtri e statistiche

    public ChiamataService (String nomeCitta, String nazione){
        this.parserCitta = new ParserCitta(nomeCitta, nazione);
    }

    public JSONObject elaboraChiamata() throws IOException, ParseException {
        this.citta = this.parserCitta.leggiDati();

        JSONObject risultato = new JSONObject();
        JSONObject identificatoreCitta = new JSONObject();
        identificatoreCitta.put("id", this.citta.getId());
        identificatoreCitta.put("nome", this.citta.getNome());
        identificatoreCitta.put("nazione", this.citta.getNazione());
        risultato.put("città", identificatoreCitta);

        JSONArray arrayPress = new JSONArray();
        for (int i=0; i < this.citta.getPressioni().size(); i++){
            JSONObject datoPressione = new JSONObject();
            datoPressione.put("dt", this.citta.getPressioni().get(i).getData().getDataEpoch());
            datoPressione.put("dtFormat", this.citta.getPressioni().get(i).getData().getDataFormattata());
            datoPressione.put("pressione", this.citta.getPressioni().get(i).getValore());
            datoPressione.put("pressioneMare", this.citta.getPressioni().get(i).getValoreMare());
            datoPressione.put("pressioneSuolo", this.citta.getPressioni().get(i).getValoreSuolo());
            arrayPress.add(datoPressione);
        }
        risultato.put("pressioni", arrayPress);

        JSONArray arrayTemp = new JSONArray();
        for (int i=0; i < this.citta.getTemperatura().size(); i++){
            JSONObject datoTemperatura = new JSONObject();
            datoTemperatura.put("dt", this.citta.getTemperatura().get(i).getData().getDataEpoch());
            datoTemperatura.put("dtFormat", this.citta.getTemperatura().get(i).getData().getDataFormattata());
            datoTemperatura.put("temperatura", this.citta.getTemperatura().get(i).getValoreReale());
            datoTemperatura.put("tempPercepita", this.citta.getTemperatura().get(i).getValorePercepito());
            datoTemperatura.put("tempMin", this.citta.getTemperatura().get(i).getValoreMinimo());
            datoTemperatura.put("tempMax", this.citta.getTemperatura().get(i).getValoreMassimo());

            arrayTemp.add(datoTemperatura);
        }
        risultato.put("temperature", arrayTemp);
        return risultato;
    }

}
