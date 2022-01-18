package it.univpm.utils;

import it.univpm.services.ChiamataService;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <b>Classe</b> di utilita' che si occupa di gestire la creazione e la scrittura/aggiornamento di file JSON. Utilizzata per l'accumulo di dati.
 *
 * @author nicolobartolini
 * @author riccardopostacchini
 */
public class GestioneFile {

    /**
     * L'intervallo di tempo tra una stampa di file e l'altra
     */
    private static final long PERIODO_TIMER = 1000 * 60 * 60 * 3; // 3 ORE

    /**
     * <b>Metodo</b> <i>statico</i> che crea la stringa del percorso al quale si trovera' il file di accumulo dati corrispondente a una determinata citta'.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @return <code>String</code> - Stringa contentente il percorso del file di accumulo dati.
     */
    public static String creaPercorso(String nomeCitta, String nazione) {
        return System.getProperty("user.dir") + "\\" + nomeCitta + "_" + nazione + "_" + "PressioniOgniOra.json";
    }

    /**
     * <b>Metodo</b> <i>statico</i> che crea un'istanza della classe <code>File</code> associata al percorso creato con il metodo <code>creaPercorso(String, String)</code>. Inoltre, se il file al percorso specificato non esiste lo crea e vi scrive un JSONArray vuoto.
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     * @return <code>File</code> di accumulo dati.
     */
    private static File creaFile(String nomeCitta, String nazione) {

        String percorso = creaPercorso(nomeCitta, nazione);
        File file = new File(percorso);

        try {
            // Se il file non esiste lo crea
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("[]");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * <b>Metodo</b> <i>statico</i> che aggiorna, ogni {@link #PERIODO_TIMER} (3 ore), il file di accumulo dati relativo a una citta' con i dati attuali relativi alla pressione (e la corrispondente data e ora).
     *
     * @param nomeCitta <b>Nome</b> della citta'.
     * @param nazione   <b>Nazione</b> della citta'.
     */
    public static void aggiornaFileJSON(String nomeCitta, String nazione) {

        File file = creaFile(nomeCitta, nazione);
        // Crea un timer
        Timer timer = new Timer();
        // Crea un'azione timerata
        TimerTask azione = new TimerTask() {
            // L'azione che viene controllata dal timertask
            @Override
            public void run() {
                JSONParser parser = new JSONParser();
                try {
                    ChiamataService service = new ChiamataService(nomeCitta, nazione);
                    JSONObject dati = service.elaboraChiamata();
                    // Crea un oggetto che viene associato al risultato del parsing del file json
                    Object o = parser.parse(new FileReader(creaPercorso(nomeCitta, nazione)));
                    // L'oggetto appena creato (contenente il risultato del parsing) viene associato a un JSONArray che quindi conterrà i dati che erano già presenti nel file
                    JSONArray fileGiaPresente = (JSONArray) o;

                    // I dati relativi alle pressioni dell'API di OpenWeather vengono associati a un JSONArray
                    JSONArray pressioni = (JSONArray) dati.get("pressioni");
                    // Crea un JSONObject e vi associa la pressione "attuale" con la corrispettiva data e ora
                    JSONObject datoAttuale = new JSONObject();
                    datoAttuale.put("pressione", ((JSONObject) pressioni.get(0)).get("pressione"));
                    datoAttuale.put("dtFormat", ((JSONObject) pressioni.get(0)).get("dtFormat"));
                    datoAttuale.put("dt", ((JSONObject) pressioni.get(0)).get("dt"));
                    // Aggiunge il JSONObject con i dati attuali al JSONArray contenente i dati già presenti nel file solo se l'ultimo dato aggiunto corrisponde ad un orario diverso
                    if (!(((JSONObject) fileGiaPresente.get(fileGiaPresente.size() - 1)).get("dt").equals(datoAttuale.get("dt")))) {
                        fileGiaPresente.add(datoAttuale);

                        // Crea un FileWriter associato al file da modificare
                        FileWriter fileWriter = new FileWriter(creaPercorso(nomeCitta, nazione));
                        // Cancella ciò che c'era scritto nel file e lo sostiuisce con il JSONArray aggiornato
                        fileWriter.write(fileGiaPresente.toJSONString());
                        fileWriter.close();
                    }
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
            }
        };
        // Permette di ripetere le istruzioni all'interno di run() ogni PERIODO_TIMER
        timer.schedule(azione, 0L, PERIODO_TIMER);
    }
}