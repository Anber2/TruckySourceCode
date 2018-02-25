package com.mawaqaa.trucky.Volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mawaqaa.trucky.Activity.MainBaseActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ayadi on 2/18/2018.
 */

public class ServerConnectionChannel {
    private static final String TAG = "ServerConnectionChannel";
    private int BABTAIN_BACKOFF_MULT = 2;
    private int BABTAIN_MAX_RETRIES = 2;

    public ServerConnectionChannel() {
    }

    public void doSendJsonRequest(TruckyRequest truckyRequest) {
        RequestQueue queue = VolleyUtils.getRequestQueue();
        JSONObject jsonObject = truckyRequest.jsonObject;

        TruckyJsonRequest myReq = new TruckyJsonRequest(truckyRequest.method, truckyRequest.mReqUrl, jsonObject,
                createReqSuccessListener(truckyRequest), createReqErrorListener(truckyRequest)) {
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };

        myReq.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                BABTAIN_MAX_RETRIES,
                BABTAIN_BACKOFF_MULT));
        //myReq.setHeader("Cache-Control", "no-cache");
//        myReq.setHeader("Content-Type", "application/x-www-form-urlencoded");
        queue.add(myReq);
    }

    private Response.ErrorListener createReqErrorListener(final TruckyRequest truckyRequest) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ReqErrorListener" + error.toString());
                TruckyResponse batainResponse = new TruckyResponse();
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e(TAG, String.valueOf(networkResponse.statusCode));
                }
                batainResponse.mReqUrl = truckyRequest.mReqUrl;
                MainBaseActivity.getMainBaseActivity().serviceResponseError(batainResponse);
            }
        };
    }

    private Response.Listener<JSONObject> createReqSuccessListener(final TruckyRequest truckyRequest) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "ReqSuccessListener :" + truckyRequest.mReqUrl);
                TruckyResponse batainResponse = new TruckyResponse();

                batainResponse.mReqUrl = truckyRequest.mReqUrl;
                batainResponse.jsonObject = response;
                MainBaseActivity.getMainBaseActivity().serviceResponseSuccess(batainResponse);
            }
        };
    }
}