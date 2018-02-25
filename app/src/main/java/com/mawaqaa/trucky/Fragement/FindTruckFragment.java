package com.mawaqaa.trucky.Fragement;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mawaqaa.trucky.Activity.MainActivity;


import com.mawaqaa.trucky.Activity.MainBaseActivity;
import com.mawaqaa.trucky.Models.LiveTrucksModel;
import com.mawaqaa.trucky.OtherClasses.SharedPrefsUtils;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.OtherClasses.urlClass;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import static com.mawaqaa.trucky.Activity.MainActivity.search_btn;
import static com.mawaqaa.trucky.OtherClasses.appConstants.startSpinwheel;
import static com.mawaqaa.trucky.OtherClasses.appConstants.stopSpinWheel;

public class FindTruckFragment extends MainBaseFragment implements
        OnMapReadyCallback {

    View v;
    private GoogleMap mGoogleMap;
    LocationManager lm;
    EditText truckNameSearchTXT;

    Spinner location, cuisine_type;


    LiveTrucksModel liveTrucksModel_;
    ArrayList<LiveTrucksModel> liveTrucksModelArrayList_;

    SupportMapFragment mapFrag;
    SharedPrefsUtils sharedPref;
    String areID="";
    String cusinID="";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {


            lm = (LocationManager) getActivity().getApplicationContext().getSystemService(getActivity().LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

            }
            else
            {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                appConstants.curentLongtit = location.getLongitude();
                appConstants.curentLat = location.getLatitude();

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locationListener);
            }
        }
        catch (Exception xx){}
    }

    private final LocationListener locationListener = new LocationListener()
    {
        public void onLocationChanged(Location location)
        {
            appConstants.curentLongtit = location.getLongitude();
            appConstants.curentLat = location.getLatitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.find_truck_fragment, container, false);
        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.find_truck));
        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.FindStationButton();

        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);



         search_btn.setVisibility(View.VISIBLE);
         search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Holder holder;
                holder = new ViewHolder(R.layout.search_sheet);
                final DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setContentHolder(holder)
                        .setCancelable(true)
                        .setGravity(Gravity.TOP)
                        .setBackgroundColorResId(Color.TRANSPARENT)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(DialogPlus dialog, View view) {
                                switch (view.getId())
                                {
                                    case R.id.find :
                                        dialog.dismiss();
                                        try
                                        {
                                            startSpinwheel(getContext(), false, true);

                                            new Thread(new Runnable() {
                                                @Override
                                                public void run()
                                                {

                                                    String userToken = sharedPref.getStringPreference(getActivity(), appConstants.userID_KEY);
                                                    int language = sharedPref.getIntegerPreference(getActivity(), appConstants.languageKey,1);
                                                    String areaID=areID;
                                                    String cusnID=cusinID;
                                                    String truckName=truckNameSearchTXT.getText().toString().trim();
                                                    String distanceInKilo="2";

                                                    String urlData=urlClass.getTrucksURL+language+"&api_token="+userToken+"&longitude="+appConstants.curentLongtit+"&latitude="+appConstants.curentLat+"&area_id="+areaID+"&Cousin="+cusnID+"";

                                                    if(distanceInKilo=="")
                                                    {
                                                        urlData=urlClass.getTrucksURL+language+"&api_token="+userToken+"&longitude="+appConstants.curentLongtit+"&latitude="+appConstants.curentLat+"&area_id="+areaID+"&truckName="+truckName+"&Cousin="+cusnID+"";
                                                    }
                                                    if(truckName=="")
                                                    {
                                                        urlData=urlClass.getTrucksURL+language+"&api_token="+userToken+"&longitude="+appConstants.curentLongtit+"&latitude="+appConstants.curentLat+"&area_id="+areaID+"&Cousin="+cusnID+"";
                                                    }
                                                    getAllTrucksRequest(urlData, null);

                                                }
                                            }).start();

                                        } catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                        break;

                                    case R.id.view_dialog:
                                        dialog.dismiss();
                                        break;
                                    case R.id.loc_btn:
                                        location.performClick();
                                        break;

                                    case R.id.cuisine_type_btn:
                                        cuisine_type.performClick();
                                        break;
                                }
                            }
                        })
                        .create();

                View view = dialog.getHolderView();
                location = view.findViewById(R.id.location);
                cuisine_type = view.findViewById(R.id.cuisine_type);
                truckNameSearchTXT = view.findViewById(R.id.truckNameSearch);

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Activity, android.R.layout.simple_spinner_item, MainActivity.areaArray);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                location.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(Activity, android.R.layout.simple_spinner_item, MainActivity.cuisineArray);
                spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cuisine_type.setAdapter(spinnerArrayAdapter2);

                dialog.show();

                cuisine_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(position>0)
                        {
                            cusinID = String.valueOf(MainActivity.cuisineIdArray[position - 1]);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(position>0)
                        {
                            areID = String.valueOf(MainActivity.areaIdArray[position - 1]);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });




        try
        {
            startSpinwheel(getContext(), false, true);

            new Thread(new Runnable() {
             @Override
             public void run()
             {

                 String userToken = sharedPref.getStringPreference(getActivity(), appConstants.userID_KEY);
                 int language = sharedPref.getIntegerPreference(getActivity(), appConstants.languageKey,1);


                 String urlData=urlClass.getTrucksURL+language+"&api_token="+userToken;
                 getAllTrucksRequest(urlData, null);
             }
         }).start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return v;
    }

    private  void DrawTrucksOnMap()
    {
        if (mGoogleMap != null)
        {
            mGoogleMap.clear();

            for(int i=0;i<liveTrucksModelArrayList_.size();i++)
            {
                double longt=liveTrucksModelArrayList_.get(i).getLongt();
                double lat=liveTrucksModelArrayList_.get(i).getLat();
                final String image=liveTrucksModelArrayList_.get(i).getImage();
                final String name=liveTrucksModelArrayList_.get(i).getName();
          ;
                final String position=String.valueOf(liveTrucksModelArrayList_.get(i).getPosition());


                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat, longt)).zoom(12).build();


                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat, longt)).title(name);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)).snippet(position);





                mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
                {
                    @Override
                    public View getInfoWindow(Marker marker)
                    {
                        View v = getLayoutInflater().inflate( R.layout.info_window_layout, null);
                        ImageView logo = v.findViewById(R.id.logo);
                        RelativeLayout markerViewLayout =v.findViewById(R.id.markerViewLayout);
                        TextView nameTXT = v.findViewById(R.id.name);

                        final int position= Integer.parseInt(marker.getSnippet());

                        Picasso.with(Activity).load(liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getImage()).resize(90, 90).into(logo);
                        nameTXT.setText(liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getName());

                        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker)
                            {
                                final int position= Integer.parseInt(marker.getSnippet());

                                appConstants.rest_image = liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getImage();
                                appConstants.rest_id =Integer.parseInt(liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getId());
                                appConstants.rest_name=liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getName();;
                                appConstants.rest_cusione = liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getCusin();;
                                appConstants.rest_rate = liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getRating();
                                appConstants.rest_isFavorite = Boolean.parseBoolean(liveTrucksModelArrayList_.get(liveTrucksModelArrayList_.get(position).getPosition()).getIsFav());

                                FragmentManager manager = Activity.getSupportFragmentManager();
                                FragmentTransaction ft = manager.beginTransaction();
                                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                                ft.replace(R.id.fragment_container,new TrucksMenueFragment(), "");
                                ft.addToBackStack(null);
                                ft.commit();

                                //Activity.pushFragments(new TrucksMenueFragment(), true, true);

                            }
                        });
                        return v;
                    }



                    @Override
                    public View getInfoContents(Marker marker)
                    {
                        return null;
                    }


                });

                mGoogleMap.addMarker(markerOptions);
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null)
        {

        }
    }


    private void getAllTrucksRequest(String urlPost, final JSONObject jsonObjectRequest) {

        JsonObjectRequest jsonObjReq;

        jsonObjReq = new JsonObjectRequest(Request.Method.GET, urlPost, jsonObjectRequest, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {

                try
                {
                    if(response !=null)
                    {
                        stopSpinWheel();

                        JSONObject jsonObjResp = new JSONObject(response.toString());

                        String status=jsonObjResp.getString("status");


                        if(status.equalsIgnoreCase("true"))
                        {
                            liveTrucksModelArrayList_=new ArrayList<>();

                            JSONArray arr=jsonObjResp.getJSONArray("data");
                            for(int i=0;i<arr.length();i++)
                            {
                                try {
                                    JSONObject obj = arr.getJSONObject(i);
                                    String id = obj.getString("id");
                                    String name = obj.getString("name");
                                    double lat = Double.parseDouble(obj.getString("lat"));
                                    double longt = Double.parseDouble(obj.getString("long"));
                                    String image = obj.getString("image");
                                    image=urlClass.imageBaseURL+image;
                                    String availb =obj.getString("availability");
                                    String rating =obj.getString("rank");
                                    String cusin ="Multiple Cusins";//obj.getString("cusin");
                                    String isFav =obj.getString("is_faverat");

                                    liveTrucksModel_ = new LiveTrucksModel(id, name, longt, lat, image,availb,rating,cusin,isFav,i);
                                    liveTrucksModelArrayList_.add(liveTrucksModel_);

                                }

                                catch (Exception xx){}


                            }
                            if(liveTrucksModelArrayList_.size()>0)
                            {
                                DrawTrucksOnMap();

                            }


                        }
                        else
                        {
                            Toast.makeText(getActivity(), jsonObjResp.getString("msg"), Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        stopSpinWheel();
                        Toast.makeText(getActivity(), "server error", Toast.LENGTH_LONG).show();

                    }


                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    stopSpinWheel();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
                stopSpinWheel();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                appConstants.appTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjReq);
    }


}
