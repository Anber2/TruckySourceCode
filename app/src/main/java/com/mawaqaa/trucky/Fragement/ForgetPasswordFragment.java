package com.mawaqaa.trucky.Fragement;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mawaqaa.trucky.Activity.MainActivity;
import com.mawaqaa.trucky.OtherClasses.urlClass;
import com.mawaqaa.trucky.R;
import com.mawaqaa.trucky.interfaces.BottomBarButtonClickState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2/5/2018.
 */

public class ForgetPasswordFragment extends MainBaseFragment {
    private static String TAG = "ForgetPasswordFrag";
    //View
    View v;

    //editText
    EditText forgetPass_email_EdTxt;
    //Button
    Button forgetPass_back_btn, forgetPass_resetPass_btn;
    String email_txt;
    RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.forget_password_fragment, container, false);

        ((MainActivity) getActivity()).setTxtInActionBarTitle(getResources().getString(R.string.forger_pass_title));
        queue = Volley.newRequestQueue(Activity);
        BottomBarButtonClickState buttonClickState = (BottomBarButtonClickState) Activity;
        buttonClickState.MyProfileButton();
        initView(v);

        return v;
    }

    private void initView(View v) {

        //editText
        forgetPass_email_EdTxt = v.findViewById(R.id.forgetPass_email_EdTxt);
        //Button
        forgetPass_back_btn = v.findViewById(R.id.forgetPass_back_btn);
        forgetPass_resetPass_btn = v.findViewById(R.id.forgetPass_resetPass_btn);

        //Button on click
        forgetPass_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity.pushFragments(new LoginFragment(), false, true);

            }
        });

        forgetPass_resetPass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()) {
                    return;
                }
                DoForgetService();
            }
        });

    }

    private void DoForgetService() {
        if (Activity.isNetworkAvailable()) {
            String url = urlClass.forgetPassURL + email_txt;
            final JsonObjectRequest jsonOforget = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                Log.d(TAG, response.toString());

                                Boolean IsSuccess = response.getBoolean("status");
                                if (IsSuccess) {
                                    String msg = response.getString("msg");
                                    Toast.makeText(Activity, msg, Toast.LENGTH_LONG).show();

                                    Activity.onBackPressed();
                                } else {
                                    String msg = response.getString("msg");
                                    Toast.makeText(Activity, msg, Toast.LENGTH_LONG).show();
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
            jsonOforget.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                    2,
                    2));
            queue.add(jsonOforget);
        } else {
            Toast.makeText(Activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateEmail() {
        email_txt = forgetPass_email_EdTxt.getText().toString().trim();

        if (email_txt.isEmpty() || !isValidEmail(email_txt)) {
            Toast.makeText(Activity, getString(R.string.plz_enter_email), Toast.LENGTH_SHORT).show();
            requestFocus(forgetPass_email_EdTxt);
            return false;
        } else {
        }
        return true;
    }

    private boolean isValidEmail(String emailS) {
        return !TextUtils.isEmpty(emailS) && android.util.Patterns.EMAIL_ADDRESS.matcher(emailS).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            Activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
