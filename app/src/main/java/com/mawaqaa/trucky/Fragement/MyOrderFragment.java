package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Adapter.MyOrderAdapter;
import com.mawaqaa.trucky.Fragement.MainBaseFragment;
import com.mawaqaa.trucky.Models.MyOrderModel;
import com.mawaqaa.trucky.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends MainBaseFragment {

    View v ;
    RecyclerView orders_recy ;
    private LinearLayoutManager linearLayoutManager;
    MyOrderAdapter myOrderAdapter ;
    List<MyOrderModel> ordersList = new ArrayList<MyOrderModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.my_order_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.my_orders));

        orders_recy = v.findViewById(R.id.orders_recy);
        linearLayoutManager = new LinearLayoutManager(Activity);
        orders_recy.setLayoutManager(linearLayoutManager);

        fillOrderData();

        return v ;
    }

    private void fillOrderData() {
        ordersList.clear();
        for (int p = 0 ; p <3 ; p++){
            ordersList.add(new MyOrderModel(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""

            ));
        }

        myOrderAdapter = new MyOrderAdapter(Activity,ordersList);
        orders_recy.setAdapter(myOrderAdapter);
    }

}
