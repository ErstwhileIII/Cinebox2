package com.velocikey.android.learning.cinebox2.webinfo.movie;

/**
 * Created by Joseph White on 27-Aug-2015
 */
public class MovieReviewInfo {
    // Class fields
    private static final String LOG_TAG = MovieReviewInfo.class.getSimpleName();
    private static final String MOVIE_REVIEW_PREFIX = "https://www.themoviedb.com/review/";
    // Object Fields
    private int id;
    private String author;
    private String content;
    private String url;
    private int movieId;


    public MovieReviewInfo(int id, String author, String content, String url, int movieId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
        this.movieId = movieId;
    }

    public int getId() {
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
        return MOVIE_REVIEW_PREFIX + id;
    }

    public int getMovieId() {
        return movieId;
    }
}
