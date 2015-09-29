package com.velocikey.android.learning.cinebox2.webinfo.movie.data;

import android.content.ContentValues;

import com.velocikey.android.learning.cinebox2.webinfo.movie.MovieInfo;
import com.velocikey.android.learning.cinebox2.webinfo.movie.MovieReviewInfo;
import com.velocikey.android.learning.cinebox2.webinfo.movie.MovieVideoInfo;

/**
 * Created by Joseph White on 14-Aug-2015
 *
 * @since 1.0
 */
public class MovieDBUtility {
    // Class fields
    private static final String LOG_TAG = MovieDBUtility.class.getSimpleName();
    // Object Fields

    /**
     * Utility to help create the ContentValues to insert movie information into the database
     *
     * @param movieInfo the movie entry from which to create ContentValues
     * @return all movie information in a ContentValues object
     */
    public static ContentValues getMovieValues(MovieInfo movieInfo) {
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COL_id, movieInfo.getMovieId());
        values.put(MovieContract.MovieEntry.COL_title, movieInfo.getTitle());
        values.put(MovieContract.MovieEntry.COL_releaseDate, movieInfo.getReleaseDate());
        values.put(MovieContract.MovieEntry.COL_popularity, movieInfo.getPopularity());
        values.put(MovieContract.MovieEntry.COL_rating, movieInfo.getRating());
        values.put(MovieContract.MovieEntry.COL_favorite, movieInfo.getFavorite());
        values.put(MovieContract.MovieEntry.COL_posterPath, movieInfo.getPosterPath());
        values.put(MovieContract.MovieEntry.COL_overview, movieInfo.getOverview());
        return values;
    }

    public static ContentValues getMovieVideos(MovieVideoInfo movieVideoInfo) {
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieVideo.COL_id, movieVideoInfo.getId());
        values.put(MovieContract.MovieVideo.COL_movie_id, movieVideoInfo.getMovieId());
        values.put(MovieContract.MovieVideo.COL_language, movieVideoInfo.getLanguage());
        values.put(MovieContract.MovieVideo.COL_name, movieVideoInfo.getName());
        values.put(MovieContract.MovieVideo.COL_site, movieVideoInfo.getSite());
        values.put(MovieContract.MovieVideo.COL_size, movieVideoInfo.getSize());
        values.put(MovieContract.MovieVideo.COL_type, movieVideoInfo.getType());
        return values;
    }

    public static ContentValues getMovieReviews(MovieReviewInfo movieReviewInfo) {
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieReview.COL_id, movieReviewInfo.getId());
        values.put(MovieContract.MovieReview.COL_movie_id, movieReviewInfo.getMovieId());
        values.put(MovieContract.MovieReview.COL_author, movieReviewInfo.getAuthor());
        values.put(MovieContract.MovieReview.COL_content, movieReviewInfo.getContent());
        values.put(MovieContract.MovieReview.COL_url, movieReviewInfo.getUrl());

        return values;
    }
}
