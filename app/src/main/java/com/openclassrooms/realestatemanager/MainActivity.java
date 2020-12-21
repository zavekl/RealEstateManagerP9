package com.openclassrooms.realestatemanager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<RealEstate> mItemRealEstate;
    private Drawable drawable1, drawable2, drawable3, drawable4, drawable5, drawable6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mRecyclerView = findViewById(R.id.rv_real_estate);
        setRV();
    }

    private void setRV() {
        setDatasRV();
        ListRealEstateRVAdapter mAdapter = new ListRealEstateRVAdapter(mItemRealEstate, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDatasRV() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            drawable1 = ContextCompat.getDrawable(this, R.drawable.house_one);
            drawable2 = ContextCompat.getDrawable(this, R.drawable.house_two);
            drawable3 = ContextCompat.getDrawable(this, R.drawable.house_three);
            drawable4 = ContextCompat.getDrawable(this, R.drawable.house_four);
            drawable5 = ContextCompat.getDrawable(this, R.drawable.house_five);
            drawable6 = ContextCompat.getDrawable(this, R.drawable.house_six);
        }

        mItemRealEstate = Arrays.asList(
                new RealEstate("Maison1", 120000, 520.25f, 16, "Description1",
                        "adresse1", "Rien", drawable1, false, "12/2/2019", "0", "Jean"),
                new RealEstate("Maison2", 210000, 520.25f, 16, "Description2",
                        "adresse2", "Rien", drawable2, false, "12/2/2019", "0", "Jean"),
                new RealEstate("Maison3", 170000, 520.25f, 16, "Description3",
                        "adresse3", "Rien", drawable3, false, "12/2/2019", "0", "Jean"),
                new RealEstate("Maison4", 260000, 520.25f, 16, "Description4",
                        "adresse4", "Rien", drawable4, false, "12/2/2019", "0", "Jean"),
                new RealEstate("Maison5", 190000, 520.25f, 16, "Description5",
                        "adresse5", "Rien", drawable5, false, "12/2/2019", "0", "Jean"),
                new RealEstate("Maison6", 230000, 520.25f, 16, "Description6",
                        "adresse6", "Rien", drawable6, false, "12/2/2019", "0", "Jean"));
    }
}
