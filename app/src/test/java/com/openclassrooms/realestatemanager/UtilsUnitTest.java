
package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void getTodayDate2True() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSS");
        assertTrue("Invalid format returned", isValidDate(format, Utils.getTodayDate2()));
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
        String s = "76 New York Ave, Halesite, NY 11743, USA"; //\w{1,}\,\s\w{1,}\,\s\w{1,} FR
        assertTrue(Utils.validateAddress(s));
    }

    @Test
    public void validateAddressFalse() {
        String s = "1 rue des fleur, Paris";//\d\w{1,5}(\s\D{1,}){1,}\,(\s\D{1,}){1,}\,\s\w{1,}\s{0,1}\d{0,5}\,\s\D{1,}
        assertFalse(Utils.validateAddress(s));
    }

    private boolean isValidDate(SimpleDateFormat format, String s) {
        try {
            format.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}