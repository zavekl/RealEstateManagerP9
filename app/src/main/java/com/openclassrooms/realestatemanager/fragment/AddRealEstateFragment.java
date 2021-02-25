package com.openclassrooms.realestatemanager.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.adapter.AddGridViewAdapter;
import com.openclassrooms.realestatemanager.model.Address;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.utils.Utils;
import com.openclassrooms.realestatemanager.viewmodel.AddRealEstateViewModel;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.openclassrooms.realestatemanager.Constants.REQUEST_CODE;
import static com.openclassrooms.realestatemanager.utils.Utils.getTodayDate2;

public class AddRealEstateFragment extends Fragment {
    private static final String TAG = "AddRealEstateFragment";

    private AddRealEstateViewModel mViewModel;

    private GridView mGridView;
    private AddGridViewAdapter mAddGridViewAdapter;

    private final List<Bitmap> mListBitmap = new ArrayList<>();
    private final List<String> mListBitmapNameFile = new ArrayList<>();

    private NestedScrollView mNestedScrollView;

    private AutoCompleteTextView mTIType;

    private TextInputEditText mTIDescription, mTIPrice, mTINumberAndStreet, mTIPostalNumber;
    private TextInputEditText mTITown, mTISurface, mTIRoom, mTIBedroom, mTIBathroom;

    private Button mButtonCreate;

    private ProgressBar mProgressBar;

    public static AddRealEstateFragment newInstance() {
        return new AddRealEstateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_real_estate_fragment, container, false);

        mTIType = view.findViewById(R.id.filled_exposed_dropdown);

        mGridView = view.findViewById(R.id.gridview_add);

        mTIPrice = view.findViewById(R.id.ti_price);
        mTIDescription = view.findViewById(R.id.ti_description);
        mTINumberAndStreet = view.findViewById(R.id.ti_location1);
        mTIPostalNumber = view.findViewById(R.id.ti_location2);
        mTITown = view.findViewById(R.id.ti_location3);
        mTISurface = view.findViewById(R.id.ti_surface);
        mTIRoom = view.findViewById(R.id.ti_room);
        mTIBedroom = view.findViewById(R.id.ti_bedroom);
        mTIBathroom = view.findViewById(R.id.ti_bathroom);

        mButtonCreate = view.findViewById(R.id.b_create);

        mNestedScrollView = view.findViewById(R.id.add_scrollview);

        mProgressBar = view.findViewById(R.id.progress_bar);
        mProgressBar.bringToFront();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddRealEstateViewModel.class);

        //Create TextInputUtils
        mViewModel.createTextInputUtils(mTIType, mTIPrice, mTIDescription,
                mTINumberAndStreet, mTIPostalNumber, mTITown, mTISurface, mTIRoom, mTIBedroom, mTIBathroom,
                requireContext());

        //Create the add image
        mListBitmap.add(Utils.getBitmap(R.drawable.ic_add_circle_outline_black_24dp, requireContext()));

        //Set GridView
        mAddGridViewAdapter = new AddGridViewAdapter(mListBitmap, requireContext());
        mGridView.setAdapter(mAddGridViewAdapter);

        onClickGridViewItem();
        onClickCreateButton();
        setDropDownTextInput();
    }

    //Start intent to choose image in internal storage of device
    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, requireContext().getResources().getString(R.string.image_intent)), REQUEST_CODE);
    }

    //Click on itemView to add image in device with pickImage() method
    private void onClickGridViewItem() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: count : " + mAddGridViewAdapter.getCount() + "/   position : " + position);
                //If click on the last item of the GridView
                if (mAddGridViewAdapter.getCount() == position + 1 && mAddGridViewAdapter.getCount() <= 5) {
                    Log.d(TAG, "onItemClick: Last item clicked, pick an image");
                    pickImage();
                } else if (position == 5) {   //Block the click because no more can appear on screen ( max 6 )
                    pickImage();
                    mGridView.setClickable(false);
                    mGridView.setFocusable(false);
                    mGridView.setEnabled(false);
                    Log.d(TAG, "onItemClick: no more image can appear on screen now max 6");
                }
            }
        });
    }

    //Get the photo of intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            //Get the image by uri and transform it in bitmap
            Uri uri = data.getData();
            try {
                Bitmap mBitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                //Set grid view item
                mAddGridViewAdapter.addItem(mBitmap);    //Or create another adapter with the data
                mGridView.setAdapter(mAddGridViewAdapter);

                //Get unique ID of file to save in memory
                String mNameFile = Utils.getTodayDate2().replace("/", ":");
                mListBitmapNameFile.add(mNameFile);
            } catch (IOException e) {
                Log.e(TAG, "onActivityResult: ", e);
            }
        }
    }

    //Set text to choose in drop down text input
    private void setDropDownTextInput() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, mViewModel.getItems());
        mTIType.setAdapter(adapter);
    }

    //Insert RealEstate item in database
    private void onClickCreateButton() {
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + mViewModel.validateTextInput());
                if (mViewModel.validateTextInput()) {
                    Log.d(TAG, "onClick: create RealEstate");
                    //TODO ADDRESS ON UI
                    Address address = new Address(mTINumberAndStreet.getText().toString(), mTINumberAndStreet.getText().toString(),
                            mTIPostalNumber.getText().toString(), mTITown.getText().toString());

                    RealEstate realEstate = new RealEstate(mTIType.getText().toString(), Integer.parseInt(mTIPrice.getText().toString()), mTISurface.getText().toString(),
                            Integer.parseInt(mTIRoom.getText().toString()), Integer.parseInt(mTIBedroom.getText().toString()),
                            Integer.parseInt(mTIBathroom.getText().toString()), mTIDescription.getText().toString(), address, "A FAIRE",
                            false, mListBitmapNameFile, getTodayDate2(), "A FAIRE", "AGENT 1");

                    mViewModel.insert(realEstate);

                    //TODO DIALOG FRAGMENT HERE WITH PROGRESS BAR
                    new AddImageFileTask(AddRealEstateFragment.this).execute();

                    mNestedScrollView.fullScroll(View.FOCUS_UP);

                } else {
                    Log.d(TAG, "onClick: can't create");
                }
            }
        });
    }

    //Asynktask which permit to make progress bar and to don't block the main thread
    private static class AddImageFileTask extends AsyncTask<Void, Double, Void> {
        private final WeakReference<AddRealEstateFragment> mAddRealEstateFragment;

        private AddImageFileTask(AddRealEstateFragment addRealEstateFragment) {
            mAddRealEstateFragment = new WeakReference<>(addRealEstateFragment);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");

            AddRealEstateFragment fragment = mAddRealEstateFragment.get();
            if (fragment != null) {
                double size = fragment.mListBitmapNameFile.size();
                for (int i = 0; i < size; i++) {
                    fragment.mViewModel.setImageInInternalMemory(fragment.mListBitmapNameFile.get(i), fragment.mListBitmap.get(i));
                    Double[] percentage = new Double[1];

                    percentage[0] = 100 * ((i + 1) / size);

                    Log.d(TAG, "doInBackground:  i/size = " + i + "/" + size + " / Calculate =  " + percentage[0]);
                    if (percentage[0] > 100) {
                        percentage[0] = 100.0;
                    }
                    publishProgress(percentage[0]);
                }
            }
            return null;
        }

        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            AddRealEstateFragment fragment = mAddRealEstateFragment.get();
            if (fragment != null) {
                fragment.mProgressBar.setVisibility(View.VISIBLE);
                fragment.mButtonCreate.setEnabled(false);
                fragment.mButtonCreate.setClickable(false);
            }
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            Log.d(TAG, "onProgressUpdate: ");
            super.onProgressUpdate(values);
            AddRealEstateFragment fragment = mAddRealEstateFragment.get();
            if (fragment != null) {
                Log.d(TAG, "onProgressUpdate: " + values[0]);
                double percentage = values[0];
                fragment.mProgressBar.setProgress((int)percentage);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AddRealEstateFragment fragment = mAddRealEstateFragment.get();
            if (fragment != null) {
                fragment.mProgressBar.setVisibility(View.INVISIBLE);

                fragment.requireActivity().getSupportFragmentManager().beginTransaction()
                        .remove(Objects.requireNonNull(fragment.requireActivity().getSupportFragmentManager().findFragmentById(R.id.description_fragment)))
                        .commit();

                MainActivity.revealViewPager();
            }
        }
    }
}