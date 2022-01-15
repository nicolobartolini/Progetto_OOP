package it.univpm.utils;

import com.opencsv.CSVWriter;
import it.univpm.services.ChiamataAPI;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GestioneFile {

    // L'intervallo di tempo tra una stampa di file e l'altra
    private static final long PERIODO_TIMER = 10000; // * 60 * 60 * 3;

    // Crea la stringa del percorso in cui viene salvato il file
    public static String creaPercorso(String nomeCitta, String nazione, String tipoFile) {
        return System.getProperty("user.dir") + "\\" + nomeCitta + "_" + nazione + "_" + "PressioniOgniOra." + tipoFile;
    }

    // crea il file al percorso specificato
    private static File creaFile (String nomeCitta, String nazione, String tipoFile){

        String percorso = creaPercorso(nomeCitta, nazione, tipoFile);

        File file = new File(percorso);

        try {
            // Se il file non esiste lo crea
            if (!file.exists())
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(percorso);
                fileWriter.write("[]");
                fileWriter.flush();
                fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    // Aggiorna (o crea) il file JSON con i dati della pressione ogni PREIODO_TIMER (3 ore)
    public static void aggiornaFileJSON(String nomeCitta, String nazione, JSONObject dati) throws IOException{
        File file = creaFile(nomeCitta, nazione, "json");
        // Crea un timer
        Timer timer = new Timer ();
        // Crea un'azione timerata
        TimerTask azione = new TimerTask () {
            @Override
            // L'azione che viene controllata dal timertask
            public void run () {
                JSONParser parser = new JSONParser();
                try {
                    Object o = parser.parse(new FileReader(creaPercorso(nomeCitta, nazione, "json")));
                    JSONArray fileGiaPresente = (JSONArray) o;

                    JSONArray pressioni = (JSONArray) dati.get("pressioni");
                    JSONObject datoAttuale = new JSONObject();
                    datoAttuale.put("pressione", ((JSONObject) pressioni.get(0)).get("pressione"));
                    datoAttuale.put("dtFormat", ((JSONObject) pressioni.get(0)).get("dtFormat"));
                    fileGiaPresente.add(datoAttuale);

                    FileWriter fileWriter = new FileWriter(creaPercorso(nomeCitta, nazione, "json"));
                    fileWriter.write(fileGiaPresente.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(azione, 0L, PERIODO_TIMER);
    }

    public static void aggiornaFileCSV(String nomeCitta, String nazione, JSONObject dati) throws IOException {
        Timer timer = new Timer ();
        FileWriter file = new FileWriter(creaFile(nomeCitta, nazione, "csv"));
        CSVWriter writer = new CSVWriter(file);
        String[] header = {"Data/Ora", "Valore pressione"};
        writer.writeNext(header);
        TimerTask azione = new TimerTask () {
            @Override
            public void run () {
                JSONArray pressioni = (JSONArray) dati.get("pressioni");
                JSONObject primaPressione = (JSONObject) pressioni.get(0);
                String[] risultato = new String[2];
                risultato[0] = (String) primaPressione.get("dtFormat");
                risultato[1] = primaPressione.get("pressione").toString();
                writer.writeNext(risultato);
            }
        };

        timer.schedule(azione, 1L, PERIODO_TIMER);
    }
}