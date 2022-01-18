package it.univpm.dataAnalytics.stats;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestStatisticheTempReale {

    private StatisticheTempReale stat;

    @BeforeEach
    void setUp() {
        double[] arrayTempReale = {20.7, 25.8, 30, 13.3, 9};
        stat = new StatisticheTempReale(arrayTempReale);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetMinimo() {
        assertEquals(9, stat.getMinimo());
    }

    @Test
    void testGetMassimo() {
        assertEquals(30, stat.getMassimo());
    }

    @Test
    void testGetMedia() {
        assertEquals(19.76, stat.getMedia());
    }

    @Test
    void testGetVarianza() {
        assertEquals(59.95, stat.getVarianza());
    }
}