package com.mawaqaa.trucky.OtherClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.mawaqaa.trucky.R;

/**
 * Created by HP on 12/24/2017.
 */

public class appConstants
{
    public  static String userID_KEY="userID";
    public  static String userEmail_KEY="userEmail";
    public  static String userPassword_KEY="userPass";
    public  static String userType_KEY="userType";
    public  static String isLoggedIn="isloggedIn";




    public static final String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";




    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception xx) {
            xx.toString();
        }
    }


    public static Dialog spinWheelDialog = null;
    public static void startSpinwheel(Context context, boolean setDefaultLifetime, boolean isCancelable) {
        try {

            if (spinWheelDialog != null && spinWheelDialog.isShowing())
                return;

            spinWheelDialog = new Dialog(context, R.style.wait_spinner_style);
            ProgressBar progressBar = new ProgressBar(context);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            spinWheelDialog.addContentView(progressBar, layoutParams);
            spinWheelDialog.setCancelable(isCancelable);
            spinWheelDialog.show();

            Handler spinWheelTimer = new Handler();
            spinWheelTimer.removeCallbacks(dismissSpinner);
            if (setDefaultLifetime) // If requested for default dismiss time.
                spinWheelTimer.postAtTime(dismissSpinner, SystemClock.uptimeMillis() + 1000);

            spinWheelDialog.setCanceledOnTouchOutside(false);
        }
        catch (Exception xx)
        {}
    }
    static Runnable dismissSpinner = new Runnable() {

        @Override
        public void run() {
            stopSpinWheel();
        }

    };

    public static void stopSpinWheel() {
        if (spinWheelDialog != null)
            try
            {
                spinWheelDialog.dismiss();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        spinWheelDialog = null;
    }

}


