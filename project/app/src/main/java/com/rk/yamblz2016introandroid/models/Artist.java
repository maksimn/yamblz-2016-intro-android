package com.rk.yamblz2016introandroid.models;

public class Artist {
    public class Cover {
        public String small;
        public String big;
    }
    public int id;
    public String name;
    public String[] genres;
    public int albums;
    public int tracks;
    public Cover cover;
    public String description;
    public static String getGenresString(String[] genres) {
        StringBuilder builder = new StringBuilder();

        for(String s : genres) {
            builder.append(s);
            builder.append(", ");
        }

        builder.setLength(genres.length > 0 ? builder.length() - 2: builder.length());

        return builder.toString();
    }
}
