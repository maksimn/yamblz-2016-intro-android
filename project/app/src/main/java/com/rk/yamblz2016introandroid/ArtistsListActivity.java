package com.rk.yamblz2016introandroid;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.*;
import com.android.volley.toolbox.*;

public class ArtistsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://download.cdn.yandex.net/mobilization-2016/artist678s.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });

        queue.add(stringRequest);
    }
}
