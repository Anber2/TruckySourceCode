package com.mawaqaa.trucky.listeners;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Activity.MainBaseActivity;
import com.mawaqaa.trucky.Fragement.ContactUsFragment;
import com.mawaqaa.trucky.Fragement.FAQFragment;
import com.mawaqaa.trucky.Fragement.MyProfileFragment;
import com.mawaqaa.trucky.Fragement.MyOrderFragment;
import com.mawaqaa.trucky.Fragement.SocialMediaFragment;
import com.mawaqaa.trucky.Fragement.TermsFragment;
import com.mawaqaa.trucky.R;

/**
 * Created by Ayadi on 11/1/2017.
 */

public class DrawerClickListeners implements View.OnClickListener {
    public static String TAG = "DrawerClickListeners";
    Context mcontext;
    DrawerLayout mDrawerLayout;
    LinearLayout textView;

    public DrawerClickListeners(Context context, DrawerLayout drawerLayout, LinearLayout textView) {
        this.mcontext = context;
        this.textView = textView;
        this.mDrawerLayout = drawerLayout;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_my_profile:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new MyProfileFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment mHomFragment = new MyProfileFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(mHomFragment, false, true);
                }
                break;


            case R.id.menu_faqs:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new FAQFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment faqFragment = new FAQFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(faqFragment, false, true);
                }
                break;

            case R.id.menu_contact_us:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new ContactUsFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment contactUsFragment = new ContactUsFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(contactUsFragment, false, true);
                }
                break;

            case R.id.menu_terms_and_conditions:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new TermsFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment termsFragment = new TermsFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(termsFragment, false, true);
                }
                break;

            case R.id.menu_social:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new SocialMediaFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment socialMediaFragment = new SocialMediaFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(socialMediaFragment, false, true);
                }
                break;

            case R.id.menu_my_order:
                Log.e(TAG, ">>>> drawer MyProfileFragment clicked.");
                if (MainActivity.getMainBaseActivity().getCurrentFragment() == new MyOrderFragment().getClass().getName()) {
                    /**In same MyProfileFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment mHomFragment = new MyOrderFragment();
                    MainBaseActivity.getMainBaseActivity().pushFragments(mHomFragment, false, true);
                }
                break;
        }
    }
}