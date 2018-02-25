package com.mawaqaa.trucky.listeners;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;

/**
 * Created by Ayadi on 11/1/2017.
 */

public class DrawerUtilities {
    protected final static String TAG = "DrawerUtils";

    public static final void closeDrawerVeiw(Context context,
                                             DrawerLayout mDrawerLayout) {
        try {
//            if (PreferenceUtil.getLanguage(context).equals(
//                    AppConstants.SAHALATH_ENGLISH)) {
//                mDrawerLayout.closeDrawer(Gravity.END);
//            } else {
//                mDrawerLayout.closeDrawer(Gravity.START);
//            }

            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        } catch (Exception e) {
            Log.e(TAG, "Exception in closeDrawer Method");
            e.printStackTrace();
        }
    }
}
