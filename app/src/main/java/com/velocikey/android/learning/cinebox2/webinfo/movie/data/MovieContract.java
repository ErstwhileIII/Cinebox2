package com.velocikey.android.learning.cinebox2.webinfo.movie.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Joseph White on 14-Aug-2015
 *
 * @since 1.0
 */
public class MovieContract {
    // Information needed for corresponding Provider
    //TODO how to put authority into string reference ??
    public static final String CONTENT_AUTHORITY = "com.velocikey.android.learning.cinebox.app";
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_MOVIE_FAVORITE = "moviefavorite";
    public static final String PATH_MOVIE_VIDEO = "movievideo";
    public static final String PATH_MOVIE_REVIEW = "moviereview";
    // Class fields
    private static final String LOG_TAG = MovieContract.class.getSimpleName();
    private static final Uri CONTENT_URI_BASE = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns {
        // information for Provider to use
        public static final Uri CONTENT_URI =
                CONTENT_URI_BASE.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        // Database information for Movie information table and column definition
        public static final String TABLE_NAME = "movie_info";
        //Remember _ID column defined in BaseColumns
        public static final String COL_id = BaseColumns._ID;
        public static final String COL_title = "title";
        public static final String COL_releaseDate = "release_date";
        public static final String COL_popularity = "popularity";
        public static final String COL_rating = "rating";
        public static final String COL_favorite = "favorite";
        public static final String COL_posterPath = "poster_path";
        public static final String COL_overview = "overview";

        public static Uri buildMovieUri() {
            return CONTENT_URI;
        }
        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class MovieFavorite implements BaseColumns {
        public static final String PATH = PATH_MOVIE_FAVORITE;
        // information for Provider to use
        public static final Uri CONTENT_URI =
                CONTENT_URI_BASE.buildUpon().appendPath(PATH).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        // Database information for Movie information table and column definition
        public static final String TABLE_NAME = "movie_favorite";
        public static final String VIEW_NAME = "movie_favorite_view";

        public static final String COL_id = BaseColumns._ID;
        public static final String COL_favorite = "favorite";

        public static Uri buildMovieUri() {
            return CONTENT_URI;
        }
        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class MovieVideo implements BaseColumns {
        /*
        https://api.themoviedb.org/3/movie/550/videos?api_key=e914a9254a8c1d706f92d0474b4a7ab5
        {"id":550,
         "results":[{"id":"533ec654c3a36854480003eb"
                    ,"iso_639_1":"en"
                    ,"key":"SUXWAEX2jlg"
                    ,"name":"Trailer 1"
                    ,"site":"YouTube"
                    ,"size":720
                    ,"type":"Trailer"}
                    ]
         }
         */
        /*
           Table movievideo
              _ID primary key
             movie_id int foreign key
             videoid int
             language text
             key text
             name text
             site text
             size int
             type text
             FOREIGN KEY (movie_id) references (movietable._ID)


         */
        public static final String PATH = PATH_MOVIE_VIDEO;
        public static final String TABLE_NAME = "movie_videos";

        // information for Provider to use
        public static final Uri CONTENT_URI =
                CONTENT_URI_BASE.buildUpon().appendPath(PATH).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        /**
         * Unique
         */
        public static final String COL_id = BaseColumns._ID;
        public static final String COL_movie_id = "movie_id";
        public static final String COL_language = "language";
        public static final String COL_key = "key";
        public static final String COL_name = "name";
        public static final String COL_site = "site";
        public static final String COL_size = "size";
        public static final String COL_type = "type";
    }

    public static final class MovieReview implements BaseColumns {

        public static final String PATH = PATH_MOVIE_REVIEW;
        public static final String TABLE_NAME = "movie_reviews";
        // information for Provider to use
        public static final Uri CONTENT_URI =
                CONTENT_URI_BASE.buildUpon().appendPath(PATH).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;


        /**
         * Unique
         */
        public static final String COL_id = BaseColumns._ID;
        public static final String COL_movie_id = "movie_id";
        public static final String COL_author = "author";
        public static final String COL_content = "content";
        public static final String COL_url = "url";
    }

    //TODO add methods that extract information from the URIs

}
