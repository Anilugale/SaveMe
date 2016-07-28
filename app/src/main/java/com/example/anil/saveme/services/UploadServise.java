package com.example.anil.saveme.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anil.saveme.controller.AppController;

import org.json.JSONObject;

/**
  Created by anil on 6/28/16.
 */
public class UploadServise extends IntentService{

    static String TAG =UploadServise.class.getName();
    public UploadServise(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

getApplicationContext();
        String url = "https://script.google.com/macros/s/AKfycbzxy4Ov8bI4aUtK3Td52jfNPdIEuVqEt8mV381ZZQCKx1e1C-L1/exec";



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, "'");
    }
}
