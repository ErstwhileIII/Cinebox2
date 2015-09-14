package com.velocikey.android.learning.cinebox2.webinfo;

/**
 * Created by Joseph White on 13-Aug-2015
 */
public class WebApiKeys {
    // Class fields
    private static final String LOG_TAG = WebApiKeys.class.getSimpleName();
    /**
     * Personal API Key for TMDB
     */
    private static final String TMDB_API_KEY_NAME = "e914a9254a8c1d706f92d0474b4a7ab5";

    /**
     * (See https://www.themoviedb.org/documentation/api)
     *
     * @return api key for The Movid Database
     */
    public static String getTMDBApiKey() {
        return TMDB_API_KEY_NAME;
    }
}
