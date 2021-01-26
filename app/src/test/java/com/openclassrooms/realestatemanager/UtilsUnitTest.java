package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UtilsUnitTest {

    @Test
    public void convertDollarToEuro() {
        assertEquals(81, Utils.convertDollarToEuro(100));
    }

    @Test
    public void convertEuroToDollar() {
        assertEquals(121.2, Utils.convertEuroToDollar(100), 1);
    }

    @Test
    public void getTodayDate2() {
        final Date date = Mockito.mock(Date.class);
        Mockito.when(date.getTime()).thenReturn(new Date().getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String toto = dateFormat.format(date.getTime());

        assertEquals(toto, Utils.getTodayDate2());
    }
}