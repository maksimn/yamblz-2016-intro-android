package com.rk.yamblz2016introandroid;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rk.yamblz2016introandroid.models.Artist;

import java.lang.reflect.Type;

public class ArtistDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String json = intent.getStringExtra("text/json");
        Type artistType = new TypeToken<Artist>(){}.getType();
        Artist artist = new Gson().fromJson(json, artistType);
        actionBar.setTitle(artist.name);
    }
}
