package com.velocikey.android.learning.cinebox2.webinfo.movie;

import android.net.Uri;

/**
 * Created by Joseph White on 27-Aug-2015
 */
public class MovieVideoInfo {
    // Class fields
    private static final String LOG_TAG = MovieVideoInfo.class.getSimpleName();
    private static final String YOUTUBE_WATCH_PREFIX = "https://www.youtube.com/watch?v=";
    // Object Fields
    private int id;
    private int movie_id;
    private String language;
    private String key;
    private String name;
    private String site;
    private int size;
    private String type;

    public MovieVideoInfo(int id, int movie_id, String language, String key, String name, String site, int size, String type) {
        this.id = id;
        this.movie_id = movie_id;
        this.language = language;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movie_id;
    }

    public String getLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public Uri getYouTubeUri() {
        return Uri.parse(YOUTUBE_WATCH_PREFIX + key);
    }
}
