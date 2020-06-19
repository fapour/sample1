package com.example.foodino.fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.foodino.R;
import com.example.foodino.customs.GlobalTextView;

public class RestaurantInformationFragment extends DialogFragment {

    GlobalTextView addressTv, phoneTv, websiteTv;

    public static RestaurantInformationFragment newInstance(String address, String phone, String website){
        RestaurantInformationFragment fragment = new RestaurantInformationFragment();

        Bundle args = new Bundle();
        args.putString("address", address);
        args.putString("phone",phone);
        args.putString("website",website);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_shape);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.fragment_restaurant_information, container,false);

        addressTv = view.findViewById(R.id.address_tv);
        phoneTv = view.findViewById(R.id.phone_tv);
        websiteTv = view.findViewById(R.id.website_tv);

        addressTv.setText(getArguments().getString("address"));
        phoneTv.setText(getArguments().getString("phone"));
        websiteTv.setText(getArguments().getString("website"));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        changeDialogSize();
    }

    //change dialog size based on screen size
    private void changeDialogSize(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels*0.8) ,
                (int) (displayMetrics.heightPixels*0.8));
    }
}
