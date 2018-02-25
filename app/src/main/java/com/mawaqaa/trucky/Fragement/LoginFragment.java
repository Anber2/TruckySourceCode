package com.mawaqaa.trucky.Fragement;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.Activity.MainBaseActivity;
import com.mawaqaa.trucky.OtherClasses.SharedPrefsUtils;
import com.mawaqaa.trucky.OtherClasses.appConstants;
import com.mawaqaa.trucky.OtherClasses.urlClass;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import org.json.JSONException;
import org.json.JSONObject;

import static com.mawaqaa.trucky.OtherClasses.appConstants.startSpinwheel;
import static com.mawaqaa.trucky.OtherClasses.appConstants.stopSpinWheel;

public class LoginFragment extends MainBaseFragment implements View.OnClickListener {
    //View
    View v;
    //editText
    EditText login_username_Etxt, login_pass_Etxt;
    //Button
    Button login_login_btn;
    //TextView
    TextView login_forgetPass_txt, login_cteateAcc_txt;
    //ImageView
    ImageView login_fb_imgv, login_mail_imgv, login_twi_imgv;
    SharedPrefsUtils sharedPref;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.login_fragment, container, false);

        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.login));

        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.MyProfileButton();

        initView(v);


        return v;
    }

    private void initView(View v) {

        //editText
        login_username_Etxt = v.findViewById(R.id.login_username_Etxt);
        login_pass_Etxt = v.findViewById(R.id.login_pass_Etxt);
        //TextView
        login_forgetPass_txt = v.findViewById(R.id.login_forgetPass_txt);
        login_cteateAcc_txt = v.findViewById(R.id.login_cteateAcc_txt);
        //ImageView
        login_fb_imgv = v.findViewById(R.id.login_fb_imgv);
        login_mail_imgv = v.findViewById(R.id.login_mail_imgv);
        login_twi_imgv = v.findViewById(R.id.login_twi_imgv);
        //Button
        login_login_btn = v.findViewById(R.id.login_login_btn);

        //TextView on click
        login_forgetPass_txt.setOnClickListener(this);
        login_cteateAcc_txt.setOnClickListener(this);
        //ImageView on click
        login_fb_imgv.setOnClickListener(this);
        login_mail_imgv.setOnClickListener(this);
        login_twi_imgv.setOnClickListener(this);
        login_login_btn.setOnClickListener(this);

    }

    private  boolean checkRule() {
        if (login_username_Etxt.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Please check the empty field", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (login_pass_Etxt.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Please check the empty field", Toast.LENGTH_SHORT).show();
            return false;
        }

        return  true;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_btn:

                try
                {
                    if(checkRule()) {
                        startSpinwheel(getContext(), false, true);

                        String deviceID = sharedPref.getStringPreference(getActivity(), appConstants.deviceToken_KEY);
                        JSONObject jo = new JSONObject();
                        jo.putOpt("userEmail", login_username_Etxt.getText().toString().trim());
                        jo.putOpt("userPass", login_pass_Etxt.getText().toString().trim());
                        jo.putOpt("deviceID", deviceID);

                        loginRequest(urlClass.loginURL, jo);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.login_forgetPass_txt:

                Activity.pushFragments(new ForgetPasswordFragment(), false, true);

                break;

            case R.id.login_cteateAcc_txt:

                Activity.pushFragments(new RegisterFragment(), false, true);

                break;

            default:
                break;
        }
    }

    private void loginRequest(String urlPost, final JSONObject jsonObjectRequest) {

        JsonObjectRequest jsonObjReq;

        jsonObjReq = new JsonObjectRequest(Request.Method.POST, urlPost, jsonObjectRequest, new Response.Listener<JSONObject>()
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
                            JSONObject obj=jsonObjResp.getJSONObject("data");
                            String user_token=obj.getString("api_token");
                            sharedPref.setStringPreference(getActivity(), appConstants.deviceToken_KEY,user_token);
                            sharedPref.setBooleanPreference(getActivity(), appConstants.isLoggedIn,true);
                            MainBaseActivity.getMainBaseActivity().getSupportFragmentManager().popBackStack();
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
