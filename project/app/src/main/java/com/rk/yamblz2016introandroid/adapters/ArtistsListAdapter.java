package com.rk.yamblz2016introandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;
import com.rk.yamblz2016introandroid.models.Artist;
import com.rk.yamblz2016introandroid.R;

import org.w3c.dom.Text;

import java.util.List;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ViewHolder> {
    private List<Artist> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ArtistsListAdapter(Context context, List<Artist> data) {
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
        holder.artistGenresTextView.setText(getGenresString(artist.genres));
        holder.albumsAndSongsTextView.setText(artist.albums + " альбомов, " +
                artist.tracks + " песен");
    }

    private String getGenresString(String[] genres) {
        StringBuilder builder = new StringBuilder();

        for(String s : genres) {
            builder.append(s);
            builder.append(", ");
        }

        builder.setLength(genres.length > 0 ? builder.length() - 2: builder.length());

        return builder.toString();
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

        ViewHolder(View itemView) {
            super(itemView);
            artistNameTextView = itemView.findViewById(R.id.artistName);
            artistGenresTextView = itemView.findViewById(R.id.artistGenres);
            albumsAndSongsTextView = itemView.findViewById(R.id.albumsAndSongs);
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