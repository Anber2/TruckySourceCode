package com.mawaqaa.trucky.Activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.mawaqaa.trucky.Fragement.FindTruckFragment;
import com.mawaqaa.trucky.Fragement.MainBaseFragment;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.Volley.TruckyResponse;
import com.mawaqaa.trucky.Volley.VolleyUtils;

/**
 * Created by Ayadi on 2/1/2018.
 */

public class MainBaseActivity extends AppCompatActivity {
    protected static MainBaseActivity BaseActivity;
    public MainBaseFragment BaseFragment;
    private static final String TAG = "MainBaseActivity";
    private Dialog spinWheelDialog;
    Handler spinWheelTimer = new Handler(); // Handler to post a runnable that

    public static final int SPINWHEEL_LIFE_TIME = 700;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "On create");
        BaseActivity = this;
     VolleyUtils.init(BaseActivity);
    }

    public void startSpinwheel(boolean setDefaultLifetime, boolean isCancelable) {
        // Log.d(TAG, "startSpinwheel"+getCurrentActivity().getClass() );
        // If already showing no need to create.
        if (spinWheelDialog != null && spinWheelDialog.isShowing())
            return;

        spinWheelDialog = new Dialog(BaseActivity, R.style.wait_spinner_style);
        ProgressBar progressBar = new ProgressBar(BaseActivity);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        spinWheelDialog.addContentView(progressBar, layoutParams);
        spinWheelDialog.setCancelable(isCancelable);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinWheelDialog.show();
            }
        });

        // start timer for SPINWHEEL_LIFE_TIME
        spinWheelTimer.removeCallbacks(dismissSpinner);
        if (setDefaultLifetime) // If requested for default dismiss time.
            spinWheelTimer.postAtTime(dismissSpinner, SystemClock.uptimeMillis() + SPINWHEEL_LIFE_TIME);

        spinWheelDialog.setCanceledOnTouchOutside(false);
    }

    public void stopSpinWheel() {
        // Log.d(TAG, "stopSpinWheel"+getCurrentActivity().getClass());
        if (spinWheelDialog != null)
            try {
                spinWheelDialog.dismiss();
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Parent is died while tryingto dismiss spin wheel dialog ");
                e.printStackTrace();
            }
        spinWheelDialog = null;
    }

    Runnable dismissSpinner = new Runnable() {

        @Override
        public void run() {
            stopSpinWheel();
        }

    };

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static MainBaseActivity getMainBaseActivity() {
        return BaseActivity;
    }

    private boolean isNeedTransaction(String backStateName) {
        boolean needTransaction = true;
        if (BaseFragment != null) {
            String baseFrag = BaseFragment.getClass().getName();
            if (baseFrag.equals(backStateName)) {
                needTransaction = false;
            } else
                needTransaction = true;
        }
        return needTransaction;
    }

    public void pushFragments(Fragment fragment, boolean shouldAnimate,
                              boolean shouldAdd) {
        FragmentManager manager = getSupportFragmentManager();
        String backStateName = fragment.getClass().getName();

        if (isNeedTransaction(backStateName)) {
            boolean fragmentPopped = manager.popBackStackImmediate(
                    backStateName, 0);

            if (!fragmentPopped) { // fragment not in back stack, create it.
                FragmentTransaction ft = manager.beginTransaction();
                if (shouldAnimate)
                    ft.setCustomAnimations(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, fragment, backStateName);
                if (shouldAdd)
                    ft.addToBackStack(backStateName);
                ft.commit();
                manager.executePendingTransactions();
            }
        }
    }

    public String getCurrentFragment() {
        String fragmentName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.e(TAG, ">>>>" + fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() == 0) {
            fragmentName = FindTruckFragment.class.getName();
            Log.e(TAG, "stack count zero" + fragmentName);
        } else {
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
            fragmentName = currentFragment.getClass().getName();
        }
        return fragmentName;
    }

    public void serviceResponseSuccess(TruckyResponse batainResponse) {
        if (batainResponse != null) {
            String reqUrl = batainResponse.mReqUrl;
            Log.d(TAG, "serviceResponseSuccess" + reqUrl);
            switch (reqUrl) {

            }
        }
    }

    public void serviceResponseError(TruckyResponse response) {
        if (response != null) {
            String reqUrl = response.mReqUrl;
            Log.d(TAG, "serviceResponseError!!!" + reqUrl);
            switch (reqUrl) {

            }
        }
    }
}
