package com.velocikey.android.learning.cinebox2.webinfo.movie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final int DATABASE_VERSION = 4;
    private static final String CreateMovieinfoTable =
            "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                    MovieContract.MovieEntry.COL_id + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " +
                    MovieContract.MovieEntry.COL_title + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_releaseDate + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_popularity + " REAL NOT NULL, " +
                    MovieContract.MovieEntry.COL_rating + " REAL NOT NULL, " +
                    MovieContract.MovieEntry.COL_posterPath + " TEXT NOT NULL, " +
                    MovieContract.MovieEntry.COL_overview + " TEXT NOT NULL " +
                    ");";
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
        //TODO use utility method to create the table statement

        Log.v(LOG_TAG, "-->onCreate: ");
        db.execSQL(CreateMovieinfoTable);
        db.execSQL(CreateMovieVideoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO currently just drop and recreate the database on version change
        Log.v(LOG_TAG, "-->onUpgrade from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieVideo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieFavorite.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO currently just drop and recreate the database on version change
        Log.v(LOG_TAG, "-->onDowngrade from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieVideo.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieFavorite.TABLE_NAME);
        onCreate(db);
    }
}
