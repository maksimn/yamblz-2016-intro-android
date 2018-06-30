package com.rk.yamblz2016introandroid.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import com.budiyev.android.imageloader.ImageLoader;
import com.rk.yamblz2016introandroid.models.Artist;
import com.rk.yamblz2016introandroid.R;
import java.util.List;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ViewHolder> {
    private List<Artist> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public ArtistsListAdapter(Context context, List<Artist> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.artist_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist artist = mData.get(position);
        holder.artistNameTextView.setText(artist.name);
        holder.artistGenresTextView.setText(Artist.getGenresString(artist.genres));

        Resources res = context.getResources();
        String albums = res.getQuantityString(R.plurals.albums, artist.albums, artist.albums);
        String tracks = res.getQuantityString(R.plurals.tracks, artist.tracks, artist.tracks);

        holder.albumsAndSongsTextView.setText(albums + ", " + tracks);

        ColorDrawable emptyPicture = new ColorDrawable(Color.rgb(210, 210, 210));
        ImageLoader.with(context)
                .from(artist.cover.small)
                .size(300, 300)
                .errorDrawable(emptyPicture)
                .load(holder.artistCoverSmall);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView artistNameTextView;
        TextView artistGenresTextView;
        TextView albumsAndSongsTextView;
        ImageView artistCoverSmall;

        ViewHolder(View itemView) {
            super(itemView);
            artistNameTextView = itemView.findViewById(R.id.artistName);
            artistGenresTextView = itemView.findViewById(R.id.artistGenres);
            albumsAndSongsTextView = itemView.findViewById(R.id.albumsAndSongs);
            artistCoverSmall = itemView.findViewById(R.id.artistCoverSmall);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}