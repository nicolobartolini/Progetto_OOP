
# Progetto OOP

Questa applicazione è un servizio web che permette di ottenere previsioni 
su pressione e temperatura di una determinata città. Grazie a filtri e statistiche
inoltre si potranno avere previsioni più specifiche
riguardanti temperature reali, percepite e pressioni dato un periodo di tempo richiesto dall'utente. 

### Indice

- [Introduzione](#Introduzione)
- [Installazione](#Installazione)
- [Rotte](#Rotte)
- [Rotta getGeneral](#getGeneral)
- [Rotta getPressioni](#getPressioni)
- [Rotta stampaFileJSON](#stampaFileJSON)
- [Rotta filtriTemperature](#filtriTemperature)
- [Rotta stats](#stats)
- [Exceptions](#exceptions)
- [TestJunit](#test)
- [Documentazione](#documentazione)
- [Autori](#autori)

<a name="Introduzione"></a>
## Introduzione

Il fine di questo programma è quello di fornire un servizio API meteorologico per gli utenti
che potranno scegliere una città e ricevere tutte le previsioni per i successivi cinque giorni
relative alle pressioni (pressione, pressione al livello del mare, pressione al livello del suolo)
e alle temperature (temperatura reale e percepita, minima e massima).
Inoltre questo servizio offre anche la possibilità di calcolare statistiche relative ai dati menzionati,
nello specifico *massimo, minimo, media e varianza*. Queste statistiche potranno poi essere filtrate
in base ad un giorno o ad una fascia oraria richiesta dall'utente.
Un'ulteriore funzione di questo programma consiste nel salvataggio di un file JSON contenente
uno storico di dati attuali relativi alle pressioni aggiornato ogni tre ore; durante lo sviluppo di questo progetto sono
stati accumulati dati sulle pressioni per le città di Ancona, Roma e Milano.
Questi file verranno poi utilizzati dalle funzioni di calcolo delle statistiche e di filtraggio.

<a name="Installazione"></a>
## Installazione

Il programma può essere installato da terminale eseguendo il comando:

```
git clone https://github.com/nicolobartolini/Progetto_OOP
```

Può essere poi eseguito, avendo installato prima Maven, con il comando

```
mvn spring-boot:run
```

Oppure più semplicemente attraverso un IDE.

<a name="Rotte"></a>
## Rotte

Le richieste dovranno essere effettuate all'indirizzo

```
http://localhost:8080
```
Le rotte relative alle **previsioni** e allo **storico** sono:

|N°| Tipo | Rotta            | Descrizione                |
|:-| :--- | :-------------   | :------------------------- |
|1 | `GET`| `/getGeneral`    | Data una città restituisce un JSONObject contenente tutte le previsioni relative a temperature e pressioni per i prossimi 5 giorni. |
|2 | `GET`| `/getPressioni`  | Data una città restituisce un JSONObject contenente tutte le previsioni relative alle pressioni per i prossimi 5 giorni.                           |
|3 | `GET`| `/stampaFileJSON`| Esegue il metodo di accumulo dati sulle pressioni di una città e restituisce una stringa contenente il percorso del file salvato.                           |

Le rotte relative alle **statistiche** e ai **filtri**:

|N°| Tipo | Rotta | Descrizione |
|--|--|--|--|
| 1 |`GET`|`/filtriTemperature`  |Data una città, un tipo di temperatura da filtrare ed un tipo di filtraggio con il relativo periodo restituisce un JSONObject contenente informazioni sulla città, sul periodo di filtraggio e il risultato del filtraggio delle statistiche (massimo, minimo, media e varianza). |
| 2 |`GET`|`/stats`|Data una città ed un tipo di dato da cui ottenere le statistiche restituisce un JSONObject contenente le informazioni sulla città e sul periodo di tempo contenente i dati e i valori statistici (massimo, minimo, media e varianza)..  |

<a name="getGeneral"></a>
### Rotta `/getGeneral`

La rotta `/getGeneral`, a partire da una città richiesta attraverso i parametri della chiamata, restituisce
un JSONObject contenente due JSONArray, uno riguardante le previsioni della pressione per i successivi cinque giorni e l'altro
riguardante le previsioni della temperatura per i successivi cinque giorni; inoltre contiene le informazioni relative alla città richiesta.
Nel caso in cui la città non venga trovata poichè inesistente si riceverà invece un'unico JSONObject contenete un messaggio di errore.

#### Parametri

- `city`: nome della città richiesta (se omesso, il valore di default è Ancona).
- `nation`: codice identificativo della nazione, i.e. IT per Italia (se omesso, il valore di default è IT).

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaGetGeneralPostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "temperature": [
        {
            "dt": 1642604400,
            "tempMax": 281.49,
            "dtFormat": "19-01-2022  16:00",
            "tempPercepita": 281.49,
            "temperatura": 281.49,
            "tempMin": 280.88
        },
             ...
        {
            "dt": 1643025600,
            "tempMax": 277.07,
            "dtFormat": "24-01-2022  13:00",
            "tempPercepita": 276.01,
            "temperatura": 277.07,
            "tempMin": 277.07
        }
    ],
    "pressioni": [
        {
            "dt": 1642604400,
            "pressioneSuolo": 1011,
            "dtFormat": "19-01-2022  16:00",
            "pressioneMare": 1027,
            "pressione": 1027
        },
             ...
        {
            "dt": 1643025600,
            "pressioneSuolo": 1019,
            "dtFormat": "24-01-2022  13:00",
            "pressioneMare": 1036,
            "pressione": 1036
        }
    ],
    "città": {
        "nazione": "IT",
        "nome": "Milan",
        "id": 3173435
    }
}
```

<a name="getPressioni"></a>
### Rotta `/getPressioni`

La rotta `/getPressioni`, a partire da una città richiesta attraverso i parametri della chiamata, restituisce
un JSONObject contenente un JSONArray riguardante le previsioni della pressione per i successivi cinque giorni e le informazioni relative alla città richiesta.
Nel caso in cui la città non venga trovata poichè inesistente si riceverà invece un'unico JSONObject contenete un messaggio di errore.

#### Parametri

- `city`: nome della città richiesta (se omesso, il valore di default è Ancona).
- `nation`: codice identificativo della nazione, i.e. IT per Italia (se omesso, il valore di default è IT).

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaGetPressioniPostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "pressioni": [
        {
            "dt": 1642604400,
            "pressioneSuolo": 1008,
            "dtFormat": "19-01-2022  16:00",
            "pressioneMare": 1031,
            "pressione": 1031
        },
             ...
        {
            "dt": 1643025600,
            "pressioneSuolo": 1015,
            "dtFormat": "24-01-2022  13:00",
            "pressioneMare": 1035,
            "pressione": 1035
        }
    ],
    "città": {
        "nazione": "IT",
        "nome": "Ascoli Piceno",
        "id": 3182749
    }
}
```

<a name="stampaFileJSON"></a>
### Rotta `/stampaFileJSON`

La rotta `/stampaFileJSON`, a partire da una città richiesta attraverso i parametri della chiamata, esegue
il metodo che aggiorna il file JSON contenente lo storico delle pressioni attuali relative alla città ogni tre ore.
Inoltre la risposta alla chiamata è un testo che indica il percorso di salvataggio del file.

#### Parametri

- `city`: nome della città richiesta (se omesso, il valore di default è Ancona).
- `nation`: codice identificativo della nazione, i.e. IT per Italia (se omesso, il valore di default è IT).

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaStampaFileJSONPostman.png)

Il testo restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
Il file è stato salvato in: C:\Users\nickb\Desktop\Progetto_OOP\Roma_IT_PressioniOgniOra.json
```
Il contenuto del file creato, dopo un certo numero di ore sarà un JSONArray del tipo:

```
[
  {
    "dt": 1641225600,
    "dtFormat": "03-01-2022  16:00",
    "pressione": 1018
  },

    ...

  {
    "dt": 1642586400,
    "dtFormat": "19-01-2022  10:00",
    "pressione": 1027
  }
]
```

<a name="filtriTemperature"></a>
### Rotta `/filtriTemperature`

La Rotta `/filtriTemperature`, a partire da una serie di parametri specificati dall'utente, restituisce un JSONObject contenente
le informazioni sulla città richiesta, le informazioni sul periodo di filtraggio richiesto e i risultati del filtraggio.
Nel caso in cui la città non venga trovata poichè inesistente si riceverà invece un'unico JSONObject contenete un messaggio di errore.

Questa rotta si divide in altre sotto-rotte in base alla scelta dei parametri di percorso:

```
/filtriTemperature/{tipoTemp}/{tipoFiltro}/{year}/{month}/{day}/{start_hour}/{end_hour}
```

#### Parametri di percorso

- `/{tipoTemp}`: tipo della temperatura da filtrare. Valori ammissibili:`[reale|percepita]`.
- `/{tipoFiltro}`: tipo di filtraggio. Valori ammissibili:`[giornaliero|fasciaOraria]`.
- `/{year}`: anno della data del periodo di filtraggio. 
- `/{month}`: mese della data del periodo di filtraggio.  
- `/{day}`: giorno della data del periodo di filtraggio.  
- `/{start_hour}`: ora iniziale della fascia oraria. 
- `/{end_hour}`: ora finale della fascia oraria.

Nel caso in cui il parametro `/{tipoFiltro}` sia giornaliero, i parametri `/{start_hour}` e `/{end_hour}` sono omissibili.

#### Parametri di richiesta

La città desiderata può essere immessa attraverso i seguenti parametri:

- `city`: nome della città richiesta (se omesso, il valore di default è Ancona).
- `nation`: codice identificativo della nazione, i.e. IT per Italia (se omesso, il valore di default è IT).

#### Esempi di utilizzo

Filtraggio giornaliero:

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaFiltroGiornalieroPostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "risultato_filtraggio": {
        "massimo": 280.78,
        "minimo": 277.07,
        "varianza": 1.75,
        "media": 278.89
    },
    "periodo_filtro": {
        "month": 1,
        "year": 2022,
        "day": 22
    },
    "città": {
        "nazione": "IT",
        "nome": "San Benedetto del Tronto",
        "id": 3168550
    }
}
```

Filtraggio fascia oraria:

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaFiltroFasciaOrariaPostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "risultato_filtraggio": {
        "massimo": 277.05,
        "minimo": 276.98,
        "varianza": 0.91,
        "media": 277.02
    },
    "periodo_filtro": {
        "fascia_oraria": {
            "start_hour": 17,
            "end_hour": 23
        },
        "month": 1,
        "year": 2022,
        "day": 21
    },
    "città": {
        "nazione": "IT",
        "nome": "Monsampolo del Tronto",
        "id": 3173126
    }
}
```

<a name="stats"></a>
### Rotta `/stats`

La Rotta `/stats`, a partire da una serie di parametri specificati dall'utente, restituisce un JSONObject contenente
le informazioni sulla città richiesta, le informazioni sul periodo in cui sono presenti i dati e i risultati delle statistiche.
Nel caso in cui la città non venga trovata poichè inesistente si riceverà invece un'unico JSONObject contenete un messaggio di errore.

Questa rotta si divide in altre sotto-rotte in base alla scelta dei parametri di percorso:

```
/stats/{tipoDato}
```

#### Parametri di percorso

- `/{tipoDato}`: tipo di dato del quale si vogliono ottenere le statistiche. Valori ammissibili:`[pressione|temperaturaReale|temperaturaPercepita]`.

Nel caso in cui il parametro `/{tipoDato}` sia `pressione`, le statistiche verranno calcolate sulla base degli storici presenti creati attraverso la rotta `/stampaFileJSON`.

#### Parametri di richiesta

La città desiderata può essere immessa attraverso i seguenti parametri:

- `city`: nome della città richiesta (se omesso, il valore di default è Ancona).
- `nation`: codice identificativo della nazione, i.e. IT per Italia (se omesso, il valore di default è IT).

#### Esempi di utilizzo

Statistiche storico pressione:

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaStatsPressionePostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "statistiche": {
        "massimo": 1029.0,
        "minimo": 1018.0,
        "varianza": 11.26046252092501,
        "media": 1023.8031496062993
    },
    "periodo": {
        "end_date": "19-01-2022  10:00",
        "start_date": "03-01-2022  16:00"
    },
    "città": {
        "nazione": "IT",
        "nome": "Milano"
    }
}
```

Statistiche temperatura:

![](https://github.com/nicolobartolini/Progetto_OOP/blob/master/img/rottaStatsTemperaturePostman.png)

Il JSONObject restituito dall'applicazione dopo una chiamata di questo tipo sarà della forma:

```
{
    "statistiche": {
        "massimo": 286.18,
        "minimo": 278.69,
        "varianza": 4.46,
        "media": 282.68
    },
    "periodo": {
        "end_date": "24-01-2022  16:00",
        "start_date": "19-01-2022  19:00"
    },
    "città": {
        "nazione": "IT",
        "nome": "Napoli"
    }
}
```

<a name="exceptions"></a>
### Exceptions

|Exceptions|Descirzione|
|:-| :--- | 
|InvalidFilterTypeException | Eccezione relativa all'immissione di un tipo di filtraggio non esistente.  |
|InvalidPeriodException| Eccezione relativa all'immissione di un periodo non valido. |
|InvalidStatsTypeException| Eccezione relativa all'immissione di un tipo di dato sbagliato riguardante le statistiche  |
|InvalidTempTypeException| Eccezione relativa all'immissione di un tipo di dato sbagliato riguardante i filtri |



<a name="test"></a>
### Test JUnit

Per verificare il corretto funzionamento di alcuni metodi e funzionalità abbiamo implementato [3 test unitari JUNit](https://github.com/nicolobartolini/Progetto_OOP/tree/master/src/test/java/it/univpm)

<a name="documentazione"></a>
### Documentazione

L'intero progetto è documentato in [Javadoc](https://github.com/nicolobartolini/Progetto_OOP/tree/master/doc)

<a name="autori"></a>
### Autori

|Autori| Contributo |
|:-| :--- | 
|Bartolini Nicolò | 50% |
|Postacchini Riccardo | 50% |
