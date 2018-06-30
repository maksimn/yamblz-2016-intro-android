package com.rk.yamblz2016introandroid;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.budiyev.android.imageloader.ImageLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rk.yamblz2016introandroid.models.Artist;

import java.lang.reflect.Type;

public class ArtistDetailActivity extends AppCompatActivity {
    private Artist model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setViewsData();
    }

    private void setViewsData() {
        setActionBar();
        setArtistCoverBigImageView();
        setArtistGenresTextView();
        setAlbumsAndSongsTextView();
        setArtistDescriptionTextView();
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String json = intent.getStringExtra("text/json");
        Type artistType = new TypeToken<Artist>(){}.getType();
        model = new Gson().fromJson(json, artistType);
        actionBar.setTitle(model.name);
    }

    private void setArtistCoverBigImageView() {
        ImageView artistCoverBigImageView = findViewById(R.id.artistCoverBig);
        ColorDrawable emptyPicture = new ColorDrawable(Color.rgb(210, 210, 210));
        ImageLoader.with(this)
                .from(model.cover.big)
                .size(300, 300)
                .errorDrawable(emptyPicture)
                .load(artistCoverBigImageView);
    }

    private void setArtistGenresTextView() {
        TextView artistGenresTextView = findViewById(R.id.artistGenres);
        artistGenresTextView.setText(Artist.getGenresString(model.genres));
    }

    private void setAlbumsAndSongsTextView() {
        Resources res = getResources();
        String albums = res.getQuantityString(R.plurals.albums, model.albums, model.albums);
        String tracks = res.getQuantityString(R.plurals.tracks, model.tracks, model.tracks);
        TextView albumsAndSongsTextView = findViewById(R.id.albumsAndSongs);
        albumsAndSongsTextView.setText(albums + " · " + tracks);
    }

    private void setArtistDescriptionTextView() {
        TextView artistDescriptionTextView = findViewById(R.id.artistDescription);
        artistDescriptionTextView.setText(model.description);
        artistDescriptionTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
