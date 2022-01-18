package it.univpm.dataAnalytics.filters;

import it.univpm.models.Citta;
import it.univpm.models.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestFiltriTemp {

    private FiltriTemp filtriTest;

    @BeforeEach
    void setUp() {
        Data primaOccorrenza = new Data(LocalDateTime.of(2022, 1, 20, 14, 0));
        Data ultimaOccorrenza = new Data(LocalDateTime.of(2022, 1, 24, 20, 0));
        Citta citta = new Citta("Ancona", "IT", primaOccorrenza, ultimaOccorrenza);
        filtriTest = new FiltriTemp(citta);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testIsGiornoValido() {
        Data giorno1 = new Data(LocalDateTime.of(2022, 1, 21, 0, 0));
        Data giorno2 = new Data(LocalDateTime.of(2022,1,10,0,0));
        Data giorno3 = new Data(LocalDateTime.of(2022,2,3,0,0));
        Data giorno4 = new Data(LocalDateTime.of(2021, 1, 21, 0, 0));
        assertTrue(filtriTest.isGiornoValido(giorno1.getDate()));
        assertFalse(filtriTest.isGiornoValido(giorno2.getDate()));
        assertFalse(filtriTest.isGiornoValido(giorno3.getDate()));
        assertFalse(filtriTest.isGiornoValido(giorno4.getDate()));
    }

    @Test
    void testIsFasciaOrariaValida() {
        Data oraIniziale1 = new Data(LocalDateTime.of(2022,1,21,14,0));
        Data oraFinale1 = new Data(LocalDateTime.of(2022,1,21,19,0));
        Data oraIniziale2 = new Data(LocalDateTime.of(2022,1,21,22,0));
        Data oraFinale2 = new Data(LocalDateTime.of(2022,1,21,10,0));
        Data oraIniziale3 = new Data(LocalDateTime.of(2022,1,15,16,0));
        Data oraFinale3 = new Data(LocalDateTime.of(2022,1,15,20,0));
        Data oraIniziale4 = new Data(LocalDateTime.of(2022,1,30,16,0));
        Data oraFinale4 = new Data(LocalDateTime.of(2022,1,30,20,0));
        Data oraIniziale5 = new Data(LocalDateTime.of(2022,1,22,13,0));
        Data oraFinale5 = new Data(LocalDateTime.of(2022,1,22,13,0));
        Data oraIniziale6 = new Data(LocalDateTime.of(2022,1,21,12,0));
        Data oraFinale6 = new Data(LocalDateTime.of(2022,1,23,20,0));
        assertTrue(filtriTest.isFasciaOrariaValida(oraIniziale1.getDate(), oraFinale1.getDate()));
        assertFalse(filtriTest.isFasciaOrariaValida(oraIniziale2.getDate(), oraFinale2.getDate()));
        assertFalse(filtriTest.isFasciaOrariaValida(oraIniziale3.getDate(), oraFinale3.getDate()));
        assertFalse(filtriTest.isFasciaOrariaValida(oraIniziale4.getDate(), oraFinale4.getDate()));
        assertFalse(filtriTest.isFasciaOrariaValida(oraIniziale5.getDate(), oraFinale5.getDate()));
        assertFalse(filtriTest.isFasciaOrariaValida(oraIniziale6.getDate(), oraFinale6.getDate()));
    }
}