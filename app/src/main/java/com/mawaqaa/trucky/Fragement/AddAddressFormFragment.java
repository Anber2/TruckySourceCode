package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

/**
 * Created by HP on 2/12/2018.
 */

public class AddAddressFormFragment extends MainBaseFragment {


    //View
    View v;
    //EditText
    EditText addAdrs_area_EdTxt, addAdrs_adrsName_EdTxt,addAdrs_block_EdTxt,addAdrs_street_EdTxt, addAdrs_avenu_EdTxt,addAdrs_building_EdTxt,addAdrs_apartmntNo_EdTxtee, addAdrs_addiAdrs_EdTxt,addAdrs_mobile_EdTxt, addAdrs_landLine_EdTxt;

    //Button
    Button addAdrs_btn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.add_address_form_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.add_new_address));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.MyProfileButton();

        initView(v);


        return v;
    }
    private void initView(View v) {



    }
}
