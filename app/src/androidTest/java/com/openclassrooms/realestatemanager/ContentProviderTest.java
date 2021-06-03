package com.openclassrooms.realestatemanager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.realestatemanager.bdd.RealEstateDatabase;
import com.openclassrooms.realestatemanager.bdd.RealEstateProvider;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ContentProviderTest {
    // FOR DATA
    private ContentResolver mContentResolver;

    // DATA SET FOR TEST
    private static final long USER_ID = 1;

    @Before
    public void setUp() {
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                RealEstateDatabase.class)
                .allowMainThreadQueries()
                .build();
        mContentResolver = ApplicationProvider.getApplicationContext().getContentResolver();
    }

    @Test
    public void getItemsWhenNoItemInserted() {
        final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(RealEstateProvider.URI_ITEM, USER_ID), null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(0));
        cursor.close();
    }

    @Test
    public void insertAndGetItem() {
        // BEFORE : Adding items
        for (ContentValues contentValues : generateItem()) {
            mContentResolver.insert(RealEstateProvider.URI_ITEM, contentValues);
        }

        // TEST
        final Cursor cursor = mContentResolver.query(RealEstateProvider.URI_ITEM, null, null, null, null);

        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(6));
        assertThat(cursor.moveToFirst(), is(true));
        assertThat(cursor.moveToNext(), is(true));
        assertThat(cursor.getString(cursor.getColumnIndexOrThrow("description")), is("This is a description"));
        assertThat(cursor.getString(cursor.getColumnIndexOrThrow("incoming_date")), is("28/05/2012"));
    }

    private List<ContentValues> generateItem() {
        List<ContentValues> listValues = new ArrayList<>();
        //Insert image
        Context context = ApplicationProvider.getApplicationContext();
        InternalFilesRepository repo = new InternalFilesRepository(context);

        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_one);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_two);
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_three);
        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_four);
        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_five);
        Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_six);

        repo.setFile("item1", bitmap1);
        repo.setFile("item2", bitmap2);
        repo.setFile("item3", bitmap3);
        repo.setFile("item4", bitmap4);
        repo.setFile("item5", bitmap5);
        repo.setFile("item6", bitmap6);

        //Create data
        final ContentValues values1 = new ContentValues();
        values1.put("type", "House");
        values1.put("price", "240000");
        values1.put("surface", "340");
        values1.put("piece_number", "23");
        values1.put("bedroom_number", "7");
        values1.put("bathroom_number", "3");
        values1.put("description", "This is a description");
        values1.put("address", "1491 Sterling Pl, Brooklyn NY 11213, USA, 40.671519004121194, -73.92901004009369");
        values1.put("pointofinterest", "6");
        values1.put("availability", "true");
        values1.put("image", "item1");
        values1.put("incoming_date", "28/05/2012");
        values1.put("date_sale", "");
        values1.put("agent", "1");

        final ContentValues values2 = new ContentValues();
        values2.put("type", "Castle");
        values2.put("price", "200000");
        values2.put("surface", "248");
        values2.put("piece_number", "23");
        values2.put("bedroom_number", "7");
        values2.put("bathroom_number", "8");
        values2.put("description", "This is a description");
        values2.put("address", "1492 Sterling Pl, Brooklyn NY 11213, USA, 40.67133000426025, -73.92927291080991");
        values2.put("pointofinterest", "16");
        values2.put("availability", "true");
        values2.put("image", "item2");
        values2.put("incoming_date", "28/09/2012");
        values2.put("date_sale", "");
        values2.put("agent", "1");

        final ContentValues values3 = new ContentValues();
        values3.put("type", "House");
        values3.put("price", "120000");
        values3.put("surface", "230");
        values3.put("piece_number", "23");
        values3.put("bedroom_number", "7");
        values3.put("bathroom_number", "3");
        values3.put("description", "This is a description");
        values3.put("address", "1493 Sterling Pl, Brooklyn NY 11213, USA, 40.67151598663605, -73.92893896155475");
        values3.put("pointofinterest", "9");
        values3.put("availability", "true");
        values3.put("image", "item3");
        values3.put("incoming_date", "28/05/2020");
        values3.put("date_sale", "");
        values3.put("agent", "1");

        final ContentValues values4 = new ContentValues();
        values4.put("type", "House");
        values4.put("price", "90000");
        values4.put("surface", "340");
        values4.put("piece_number", "23");
        values4.put("bedroom_number", "7");
        values4.put("bathroom_number", "3");
        values4.put("description", "This is a description");
        values4.put("address", "1494 Sterling Pl, Brooklyn NY 11213, USA, 40.671326315546544, -73.92920926817706");
        values4.put("pointofinterest", "12");
        values4.put("availability", "true");
        values4.put("image", "item4");
        values4.put("incoming_date", "28/05/2015");
        values4.put("date_sale", "");
        values4.put("agent", "1");

        final ContentValues values5 = new ContentValues();
        values5.put("type", "House");
        values5.put("price", "450000");
        values5.put("surface", "340");
        values5.put("piece_number", "23");
        values5.put("bedroom_number", "7");
        values5.put("bathroom_number", "3");
        values5.put("description", "This is a description");
        values5.put("address", "1495 Sterling Pl, Brooklyn NY 11213, USA, 40.67149800745375, -73.92885002956207");
        values5.put("pointofinterest", "23");
        values5.put("availability", "true");
        values5.put("image", "item5");
        values5.put("incoming_date", "23/10/2015");
        values5.put("date_sale", "");
        values5.put("agent", "1");

        final ContentValues values6 = new ContentValues();
        values6.put("type", "House");
        values6.put("price", "340000");
        values6.put("surface", "340");
        values6.put("piece_number", "23");
        values6.put("bedroom_number", "7");
        values6.put("bathroom_number", "3");
        values6.put("description", "This is a description");
        values6.put("address", "1496 Sterling Pl, Brooklyn NY 11213, USA, 40.67132262683284, -73.92914562554421");
        values6.put("pointofinterest", "6");
        values6.put("availability", "true");
        values6.put("image", "item6");
        values6.put("incoming_date", "08/09/2017");
        values6.put("date_sale", "");
        values6.put("agent", "1");

        listValues.add(values1);
        listValues.add(values2);
        listValues.add(values3);
        listValues.add(values4);
        listValues.add(values5);
        listValues.add(values6);

        return listValues;
    }
}