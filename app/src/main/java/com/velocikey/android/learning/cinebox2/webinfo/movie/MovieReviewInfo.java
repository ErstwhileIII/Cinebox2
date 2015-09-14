package com.velocikey.android.learning.cinebox2.webinfo.movie;

/**
 * Created by Joseph White on 27-Aug-2015
 */
public class MovieReviewInfo {
    // Class fields
    private static final String LOG_TAG = MovieReviewInfo.class.getSimpleName();
    private static final String YOUTUBE_WATCH_PREFIX = "https://www.youtube.com/watch?v=";
    // Object Fields
    private String id;
    private String author;
    private String content;
    private String url;
    private String movie_id;


    public MovieReviewInfo(String id, String author, String content, String url, String movie_id) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
        //TODO should we add movie information here
        this.movie_id = movie_id;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getWebPath() {
        String result = "https://www.themoviedb.com/review/" + id;
        return result;
    }
}
