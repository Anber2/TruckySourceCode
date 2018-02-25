package com.mawaqaa.trucky.Volley;

import com.android.volley.Request;

import org.json.JSONObject;

/**
 * Created by Ayadi on 2/18/2018.
 */

public class CommandFactory {
    public void sendgetCommand(String url) {

        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(createGetRequest(url));

    }

    public void sendPostCommand(String url, JSONObject jsonObject) {
        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(createPostRequest(url, jsonObject));
    }

    /*method for get method*/
    private TruckyRequest createGetRequest(String url) {
        TruckyRequest babtainRequest = new TruckyRequest();
        babtainRequest.method = Request.Method.GET;
        babtainRequest.mReqUrl = url;
        return babtainRequest;

    }

    /*method for post method*/
    private TruckyRequest createPostRequest(String url, JSONObject jsonObject) {
        TruckyRequest babtainRequest = new TruckyRequest();
        babtainRequest.method = Request.Method.POST;
        babtainRequest.mReqUrl = url;
        babtainRequest.jsonObject = jsonObject;
        return babtainRequest;

    }
}