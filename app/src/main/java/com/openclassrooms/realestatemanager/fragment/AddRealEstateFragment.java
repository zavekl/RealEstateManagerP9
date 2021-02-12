package com.openclassrooms.realestatemanager.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.model.Address;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.viewmodel.AddRealEstateViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.openclassrooms.realestatemanager.Constants.REQUEST_CODE;
import static com.openclassrooms.realestatemanager.utils.Utils.getTodayDate2;

public class AddRealEstateFragment extends Fragment {
    private static final String TAG = "AddRealEstateFragment";

    private AddRealEstateViewModel mViewModel;

    private ImageSlider mImageSlider;

    private AutoCompleteTextView mTIType;

    private TextInputEditText mTIDescription, mTIPrice, mTINumberAndStreet, mTIPostalNumber;
    private TextInputEditText mTITown, mTISurface, mTIRoom, mTIBedroom, mTIBathroom;

    private Button mButtonCreate;

    List<SlideModel> list = new ArrayList<>();

    public static AddRealEstateFragment newInstance() {
        return new AddRealEstateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_real_estate_fragment, container, false);

        mImageSlider = view.findViewById(R.id.is_add);

        mTIType = view.findViewById(R.id.filled_exposed_dropdown);

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddRealEstateViewModel.class);

        mViewModel.createTextInputUtils(mTIType, mTIPrice, mTIDescription,
                mTINumberAndStreet, mTIPostalNumber, mTITown, mTISurface, mTIRoom, mTIBedroom, mTIBathroom,
                requireContext());

        onClickCreateButton();
        setDropDownTextInput();
        createImageSlider();
    }

    //Create image slider to add photos
    private void createImageSlider() {
        list.add(new SlideModel(R.drawable.outline_add_circle_outline_black_48dp, "", null));
        mImageSlider.setImageList(list);

        onClickItemSlider();
    }

    //On click item start @pickImage() method
    private void onClickItemSlider() {
        mImageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                if (i == 1) {
                    pickImage();
                }
            }
        });
    }

    //Start intent to choose image in internal storage of device
    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, requireContext().getResources().getString(R.string.image_intent)), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && requestCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String fileString = uri.getPath();

            Bitmap bitmap = BitmapFactory.decodeFile(fileString);
//            list.add();
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
                            false, "creationDb1", getTodayDate2(), "A FAIRE", "AGENT 1");

                    mViewModel.insert(realEstate);

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .remove(Objects.requireNonNull(requireActivity().getSupportFragmentManager().findFragmentById(R.id.description_fragment)))
                            .commit();

                    MainActivity.revealViewPager();
                } else {
                    Log.d(TAG, "onClick: can't create");
                }
            }
        });
    }
}