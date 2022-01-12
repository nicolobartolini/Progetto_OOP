/* package it.univpm.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GestioneFile {

public static File creaFile (String nomeCitta, String nazione, String tipoFile){

    String percorso = System.getProperty("user.dir") + "/" + nomeCitta + "_" + nazione + "_" + "PressioniOgniOra." + tipoFile;

    File file = new File(percorso);

    if(!file.exists())
        file.createNewFile();

    return file;
}

@Scheduled (fixedRate = 1000 * 60 * 60 * 3)
    public static String aggiornaDati (File file, JSONObject dati){

    JSONArray pressioni = new JSONArray();
    pressioni = (JSONArray) dati.get("pressioni");

    JSONObject pressioneAttuale = (JSONObject) pressioni.get(0);

    JSONArray ritorno = new JSONArray();
    JSONObject dato = new JSONObject();
    dato.put("data", pressioneAttuale.get("dtFormat"));
    dato.put("pressione", pressioneAttuale.get("pressione"));
    ritorno.add(dato);
}



}

 */
