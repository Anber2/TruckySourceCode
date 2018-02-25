package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.mawaqaa.trucky.Activity.MainBaseActivity;

/**
 * Created by Ayadi on 2/1/2018.
 */

public class MainBaseFragment extends Fragment {
    private static final String TAG = "MainBaseFragment";
    public MainBaseActivity Activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "oncreate>>");
        Activity = (MainBaseActivity) this.getActivity();
    }

    public void onResume() {
        Log.d(TAG, "onResume" + this.getClass().getName());
        super.onResume();
        ((MainBaseActivity) getActivity()).BaseFragment = this;
    }
}
