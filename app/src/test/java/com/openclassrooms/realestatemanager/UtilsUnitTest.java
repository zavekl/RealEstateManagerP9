package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.whenNew;


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
    public void getTodayDate1True() {
        final Date date = Mockito.mock(Date.class);
        Mockito.when(date.getTime()).thenReturn(new Date().getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String expected = dateFormat.format(date.getTime());

        assertEquals(expected, Utils.getTodayDate1());
    }

    @Test
    public void getTodayDate1False() {
        final Date date = Mockito.mock(Date.class);
        Mockito.when(date.getTime()).thenReturn(new Date().getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        String expected = dateFormat.format(date.getTime());

        assertNotEquals(expected, Utils.getTodayDate1());
    }

    @Test
    public void getTodayDate2True() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date NOW = sdf.parse("2015-05-23 00:00:00");
        whenNew(Date.class).withNoArguments().thenReturn(NOW);
//        final Date date = Mockito.mock(Date.class);
//        Mockito.when(date.getTime()).thenReturn(new Date().getTime());
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSS");
//        String expected = dateFormat.format(date.getTime());

        assertEquals(NOW, Utils.getTodayDate2());
    }

    @Test
    public void getTodayDate2False() {
        final Date date = Mockito.mock(Date.class);
        Mockito.when(date.getTime()).thenReturn(new Date().getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        String expected = dateFormat.format(date.getTime());

        assertNotEquals(expected, Utils.getTodayDate2());
    }

    @Test
    public void getEmojiByUnicodeTrue() {
        String expected = "⚠";
        assertEquals(expected, Utils.getEmojiByUnicode(0x26A0));
    }

    @Test
    public void getEmojiByUnicodeFalse() {
        String expected = "⚠";
        assertNotEquals(expected, Utils.getEmojiByUnicode(0x26A1));
    }

    @Test
    public void validateAddressTrue() {
        String s = "1 rue des fleur, Paris, France";
        assertTrue(Utils.validateAddress(s));
    }

    @Test
    public void validateAddressFalse() {
        String s = "1 rue des fleur, Paris";
        assertFalse(Utils.validateAddress(s));
    }
}