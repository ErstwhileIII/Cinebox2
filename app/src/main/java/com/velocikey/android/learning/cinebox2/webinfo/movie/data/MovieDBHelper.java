package com.velocikey.android.learning.cinebox2.webinfo.movie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.velocikey.android.learning.cinebox2.utility.Utility;

/**
 * Created by Joseph White on 14-Aug-2015
 *
 * @since 1.0
 */
public class MovieDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "movieinfo.db";
    // Class fields
    private static final String LOG_TAG = MovieDBHelper.class.getSimpleName();
    // Database information
    private static final int DATABASE_VERSION = 1;
    private static final String CreateMovieinfoTable =
            "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                    MovieContract.MovieEntry.COL_id + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " +
                    MovieContract.MovieEntry.COL_title + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_releaseDate + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_popularity + " REAL NOT NULL, " +
                    MovieContract.MovieEntry.COL_rating + " REAL NOT NULL, " +
                    MovieContract.MovieEntry.COL_favorite + " INTEGER, " +
                    MovieContract.MovieEntry.COL_posterPath + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_overview + " TEXT NOT NULL " +
                    ");";
    private static final String CreateMovieFavoriteTable =
            "CREATE TABLE " + MovieContract.MovieFavorite.TABLE_NAME + " (" +
                    MovieContract.MovieFavorite.COL_id + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " +
                    MovieContract.MovieFavorite.COL_favorite + " INTEGER " +
                    ");";
    private static final String CreateMovieFavoriteView =
            "CREATE VIEW " + MovieContract.MovieFavorite.VIEW_NAME +
                    " AS SELECT " +
                    MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry.COL_id + ", " +
                    MovieContract.MovieEntry.COL_title + ", " +
                    MovieContract.MovieEntry.COL_releaseDate + ", " +
                    MovieContract.MovieEntry.COL_popularity + ", " +
                    MovieContract.MovieEntry.COL_rating + ", " +
                    MovieContract.MovieFavorite.TABLE_NAME + "." + MovieContract.MovieEntry.COL_favorite + ", " +
                    MovieContract.MovieEntry.COL_posterPath + ", " +
                    MovieContract.MovieEntry.COL_overview +
                    " FROM " + MovieContract.MovieEntry.TABLE_NAME +
                    " LEFT OUTER JOIN " + MovieContract.MovieFavorite.TABLE_NAME +
                    " ON " + MovieContract.MovieEntry.TABLE_NAME + "." +
                    MovieContract.MovieEntry.COL_id +
                    " = " +
                    MovieContract.MovieFavorite.TABLE_NAME + "." +
                    MovieContract.MovieFavorite.COL_id;

    private static final String CreateMovieVideoTable =
            "CREATE TABLE " + MovieContract.MovieVideo.TABLE_NAME + " (" +
                    MovieContract.MovieVideo.COL_id + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " +
                    MovieContract.MovieVideo.COL_movie_id + " INTEGER NOT NULL, " +
                    MovieContract.MovieVideo.COL_language + " TEXT NOT NULL, " +
                    MovieContract.MovieVideo.COL_key + " TEXT NOT NULL, " +
                    MovieContract.MovieVideo.COL_name + " TEXT NOT NULL, " +
                    MovieContract.MovieVideo.COL_site + " TEXT NOT NULL, " +
                    MovieContract.MovieVideo.COL_size + " TEXT NOT NULL, " +
                    MovieContract.MovieVideo.COL_type + " TEXT NOT NULL, " +
                    " FOREIGN KEY (" + MovieContract.MovieVideo.COL_movie_id + ") REFERENCES "
                    + MovieContract.MovieEntry.TABLE_NAME + "(" + MovieContract.MovieEntry.COL_id + "));";

    private static final String CreateMovieReviewTable =
            "CREATE TABLE " + MovieContract.MovieReview.TABLE_NAME + " (" +
                    MovieContract.MovieReview.COL_id + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " +
                    MovieContract.MovieReview.COL_movie_id + " INTEGER NOT NULL, " +
                    MovieContract.MovieReview.COL_author + " TEXT NOT NULL, " +
                    MovieContract.MovieReview.COL_content + " TEXT NOT NULL, " +
                    MovieContract.MovieReview.COL_url + " TEXT NOT NULL, " +
                    " FOREIGN KEY (" + MovieContract.MovieReview.COL_movie_id + ") REFERENCES "
                    + MovieContract.MovieEntry.TABLE_NAME + "(" + MovieContract.MovieEntry.COL_id + "));";
    // Object Fields
    //TODO needed?
    private final Context mContext;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v(LOG_TAG, "-->Constructor");
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO use utility method to create the table statement?

        Log.v(LOG_TAG, "-->onCreate: ");
        Log.v(LOG_TAG, "Movie: " + CreateMovieinfoTable);
        db.execSQL(CreateMovieinfoTable);
        Log.v(LOG_TAG, "Movie Favorite: " + CreateMovieFavoriteTable);
        db.execSQL(CreateMovieFavoriteTable);
        Log.v(LOG_TAG, "Movie Favorite view: " + CreateMovieFavoriteView);
        db.execSQL(CreateMovieFavoriteView);
        Log.v(LOG_TAG, "Video: " + CreateMovieVideoTable);
        db.execSQL(CreateMovieVideoTable);
        Log.v(LOG_TAG, "Review: " + CreateMovieReviewTable);
        db.execSQL(CreateMovieReviewTable);

        //TODO ensure that we reload the table .. clear the last loaded flag
        Utility.setLastQueryTime(mContext, 0L);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO currently just drop and recreate the database on version change
        Log.v(LOG_TAG, "-->onUpgrade from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieFavorite.TABLE_NAME);
        db.execSQL("DROP VIEW IF EXISTS " + MovieContract.MovieFavorite.VIEW_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieVideo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieReview.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO currently just drop and recreate the database on version change
        Log.v(LOG_TAG, "-->onDowngrade from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieFavorite.TABLE_NAME);
        db.execSQL("DROP VIEW IF EXISTS " + MovieContract.MovieFavorite.VIEW_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieVideo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieReview.TABLE_NAME);
        onCreate(db);
    }
}
