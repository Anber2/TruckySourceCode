package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Adapter.MyCartAdapter;
import com.mawaqaa.trucky.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MyCartFragment extends MainBaseFragment {

    View v;
    RecyclerView cart_recy ;
    private LinearLayoutManager linearLayoutManager;
    MyCartAdapter myCartAdapter ;
    private ArrayList<String> mDataSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.my_cart_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.my_order_bag));

        defineView();
        return v;
    }

    private void defineView() {
        cart_recy = v.findViewById(R.id.cart_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        cart_recy.setLayoutManager(linearLayoutManager);

        fillMyCartData();
    }

    private void fillMyCartData() {
        String[] adapterData = new String[]{ "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        mDataSet = new ArrayList<String>(Arrays.asList(adapterData));
        myCartAdapter = new MyCartAdapter(Activity, mDataSet);
        ((MyCartAdapter) myCartAdapter).setMode(Attributes.Mode.Single);
        cart_recy.setAdapter(myCartAdapter);
    }
}