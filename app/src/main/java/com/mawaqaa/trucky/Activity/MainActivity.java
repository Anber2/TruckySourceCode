package com.mawaqaa.trucky.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mawaqaa.trucky.Fragement.FindTruckFragment;
import com.mawaqaa.trucky.Fragement.LiveStationFragment;
import com.mawaqaa.trucky.Fragement.LoginFragment;
import com.mawaqaa.trucky.Fragement.MyProfileFragment;
import com.mawaqaa.trucky.Fragement.ResturentFragment;
import com.mawaqaa.trucky.OtherClasses.SharedPrefsUtils;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.OtherClasses.urlClass;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;
import com.mawaqaa.trucky.listeners.DrawerClickListeners;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends MainBaseActivity implements BottomBarButtonClickState, View.OnClickListener {
    public static final String TAG = "MainActivity";
    public static boolean isDrawerOpen = false;
    public static DrawerLayout drawerLayout;
    public static NavigationView mNavigationView;
    public static ImageView search_btn;
    TextView txtInActionBarTitle;
    ImageButton find_truck_btn, live_station_btn, resturent_btn, myprofile_btn, setting_btn;
    Fragment fragment;
    LinearLayout linearLayoutContainer;
    View drawerView;
    LinearLayout menu_my_profile, menu_my_order, menu_faqs, menu_contact_us, menu_terms_and_conditions, menu_social;
    SharedPrefsUtils myPref ;
    RequestQueue queue;

    public static String[] areaArray;
    public static int[] areaIdArray;

    public static String[] cuisineArray;
    public static int[] cuisineIdArray;



    Boolean isLoggedIN ;
    SharedPrefsUtils sharedPref ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_truck_main_activity);

        defineView();
        setDrawer();

        switch (appConstants.type_services){
            case 1 :
                fragment = new FindTruckFragment();
                pushFragments(fragment, false, false);
                break;

            case  2 :

                fragment = new LiveStationFragment();
                pushFragments(fragment, false, false);

                break;

            case 3:

                fragment = new ResturentFragment();
                pushFragments(fragment, false, false);
                break;
        }

        int language = myPref.getIntegerPreference(this, appConstants.languageKey, 1);
        getDataArea(language);
        getCuisine(language);
    }

    private void getCuisine(int language) {
        String url = urlClass.getAllCusinsURL+language;
        final JsonObjectRequest jsonObArea = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d(TAG,response.toString());

                            Boolean IsSuccess = response.getBoolean("status");
                            if (IsSuccess) {
                                JSONArray jArray = response.getJSONArray("data");
                                fillCuisine(jArray);
                            } else {

//                                Toast.makeText(AreaAndLanguageActivity.this, Message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e(TAG, String.valueOf(networkResponse.statusCode));
                }
            }
        }) {
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };
        jsonObArea.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                2,
                2));
        queue.add(jsonObArea);
    }



    private void getDataArea(int language) {
        String url = urlClass.getAllAreasURL+language;
        final JsonObjectRequest jsonObArea = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d(TAG,response.toString());

                            Boolean IsSuccess = response.getBoolean("status");
                            if (IsSuccess) {
                                JSONArray jArray = response.getJSONArray("data");
                                fillArea(jArray);
                            } else {

//                                Toast.makeText(AreaAndLanguageActivity.this, Message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e(TAG, String.valueOf(networkResponse.statusCode));
                }
            }
        }) {
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };
        jsonObArea.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                2,
                2));
        queue.add(jsonObArea);
    }

    private void fillArea(JSONArray jArray) {
        areaArray = new String[jArray.length() + 1];
        areaIdArray = new int[jArray.length()];

        areaArray[0] = getString(R.string.select_area);
        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jo = jArray.getJSONObject(i);
                areaArray[i + 1] = jo.getString("name");
                areaIdArray[i] = jo.getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void fillCuisine(JSONArray jArray) {
        cuisineArray= new String[jArray.length() + 1];
        cuisineIdArray = new int[jArray.length()];

        cuisineArray[0]=getString(R.string.select_cuisine);
        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jo = jArray.getJSONObject(i);
                cuisineArray[i + 1] = jo.getString("name");
                cuisineIdArray[i] = jo.getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        linearLayoutContainer = (LinearLayout) findViewById(R.id.fragment_container);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) linearLayoutContainer.getLayoutParams();
        drawerView = mNavigationView.inflateHeaderView(R.layout.menu_drawer_layout);
        linearLayoutContainer.setLayoutParams(params);
        params.setMargins(0, 0, 0, 0);

        menu_my_profile = drawerView.findViewById(R.id.menu_my_profile);
        menu_my_order = drawerView.findViewById(R.id.menu_my_order);
        menu_faqs = drawerView.findViewById(R.id.menu_faqs);
        menu_contact_us = drawerView.findViewById(R.id.menu_contact_us);
        menu_terms_and_conditions = drawerView.findViewById(R.id.menu_terms_and_conditions);
        menu_social = drawerView.findViewById(R.id.menu_social);

        menu_my_profile.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_my_profile));
        menu_my_order.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_my_order));
        menu_faqs.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_faqs));
        menu_contact_us.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_contact_us));
        menu_terms_and_conditions.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_terms_and_conditions));
        menu_social.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, menu_social));


    }

    private void defineView() {
        queue = Volley.newRequestQueue(this);
        txtInActionBarTitle = findViewById(R.id.tool_bar_title);
        find_truck_btn = findViewById(R.id.find_truck_btn);
        live_station_btn = findViewById(R.id.live_station_btn);
        resturent_btn = findViewById(R.id.resturent_btn);
        myprofile_btn = findViewById(R.id.myprofile_btn);
        setting_btn = findViewById(R.id.setting_btn);
        search_btn = findViewById(R.id.search_btn);

        find_truck_btn.setOnClickListener(this);
        live_station_btn.setOnClickListener(this);
        resturent_btn.setOnClickListener(this);
        myprofile_btn.setOnClickListener(this);
        setting_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_truck_btn:
                if (isDrawerOpen) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    isDrawerOpen = false;
                }


                pushFragments(new FindTruckFragment(), false, true);
                break;
            case R.id.live_station_btn:
                if (isDrawerOpen) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    isDrawerOpen = false;
                }


                pushFragments(new LiveStationFragment(), false, true);
                break;
            case R.id.resturent_btn:
                if (isDrawerOpen) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    isDrawerOpen = false;
                }


                pushFragments(new ResturentFragment(), false, true);
                break;
            case R.id.myprofile_btn:
                if (isDrawerOpen) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    isDrawerOpen = false;
                }

                isLoggedIN = sharedPref.getBooleanPreference(getApplicationContext(), appConstants.isLoggedIn,false);

                if(isLoggedIN)
                {
                    pushFragments(new MyProfileFragment(), false, true);
                }
                else
                {
                    pushFragments(new LoginFragment(), false, true);
                }



                break;
            case R.id.setting_btn:
                if (isDrawerOpen) {
                    Log.e(TAG, "alreadey open");
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    isDrawerOpen = false;
                    break;
                } else {
                    Log.e(TAG, "drawer is closed");
                    SettingButton();
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    isDrawerOpen = true;
                    break;
                }
        }
    }

    public void setTxtInActionBarTitle(String title) {
        txtInActionBarTitle.setText(title);
    }

    @Override
    public void FindStationButton() {
        find_truck_btn.setImageResource(R.drawable.find_station_active);
        live_station_btn.setImageResource(R.drawable.live_station_logo);
        resturent_btn.setImageResource(R.drawable.resturent);
        myprofile_btn.setImageResource(R.drawable.my_profile);
        setting_btn.setImageResource(R.drawable.setting);
    }

    @Override
    public void LiveStation() {
        live_station_btn.setImageResource(R.drawable.live_station_active);
        find_truck_btn.setImageResource(R.drawable.find_station);
        resturent_btn.setImageResource(R.drawable.resturent);
        myprofile_btn.setImageResource(R.drawable.my_profile);
        setting_btn.setImageResource(R.drawable.setting);
    }

    @Override
    public void ResturentButton() {
        resturent_btn.setImageResource(R.drawable.resturent_active);
        find_truck_btn.setImageResource(R.drawable.find_station);
        live_station_btn.setImageResource(R.drawable.live_station_logo);
        myprofile_btn.setImageResource(R.drawable.my_profile);
        setting_btn.setImageResource(R.drawable.setting);
    }

    @Override
    public void MyProfileButton() {
        myprofile_btn.setImageResource(R.drawable.my_profile_active);
        find_truck_btn.setImageResource(R.drawable.find_station);
        live_station_btn.setImageResource(R.drawable.live_station_logo);
        resturent_btn.setImageResource(R.drawable.resturent);
        setting_btn.setImageResource(R.drawable.setting);
    }

    @Override
    public void SettingButton() {
        setting_btn.setImageResource(R.drawable.setting_active);
        find_truck_btn.setImageResource(R.drawable.find_station);
        live_station_btn.setImageResource(R.drawable.live_station_logo);
        resturent_btn.setImageResource(R.drawable.resturent);
        myprofile_btn.setImageResource(R.drawable.my_profile);
    }


}
