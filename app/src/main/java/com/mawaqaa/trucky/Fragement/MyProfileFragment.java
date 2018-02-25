package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Activity.MainBaseActivity;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import java.util.Calendar;

public class MyProfileFragment extends MainBaseFragment {
    //View
    View v;
    //editText
    EditText profile_fName_EdTxt, profile_lName_EdTxt, profile_email_EdTxt, profile_date_EdTxt, profile_phone_EdTxt, profile_pass_EdTxt;
    //Button
    Button profile_edit_btn, profile_checkAddress_btn;
    //RadioGroup
    RadioGroup profile_gendor_radiogr;
    RadioButton profile_male_radioBtn, profile_female_radioBtn;
    //calender
    Calendar myCalendar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.my_profile_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.my_profile));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.MyProfileButton();

        initView(v);

        return v;
    }

    private void initView(View v) {
        //Button
        profile_checkAddress_btn = v.findViewById(R.id.profile_checkAddress_btn);

        MainActivity.search_btn.setVisibility(View.INVISIBLE);


        //on click
        profile_checkAddress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment mHomFragment = new AddressBookFragment();
                MainBaseActivity.getMainBaseActivity().pushFragments(mHomFragment, false, true);

            }
        });



    }

}
