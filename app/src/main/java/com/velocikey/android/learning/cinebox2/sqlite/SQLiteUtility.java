package com.velocikey.android.learning.cinebox2.sqlite;

import android.content.UriMatcher;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Joseph White on 16-Aug-2015
 *
 * @since 1.0
 */
class SQLiteUtility {
    // Class fields
    private static final String LOG_TAG = SQLiteUtility.class.getSimpleName();
    // Object Fields

    /**
     * Creates a SQLiteQueryBuilder from the list of tables to query.
     * Multiple tables can be specified to perform a join.
     * For example:
     * setTables("foo, bar")
     * setTables("foo LEFT OUTER JOIN bar ON (foo.id = bar.foo_id)")
     *
     * @param tables table specification
     * @return a query builder for movies
     */
    public static SQLiteQueryBuilder instanceSQLiteQueryBuilder(String tables) {
        SQLiteQueryBuilder movieQueryBuilder = new SQLiteQueryBuilder();
        movieQueryBuilder.setTables(tables);
        return movieQueryBuilder;
    }

    /**
     * Create a UriMatcher from a list of all the uris to match
     *
     * @param matches table of Authority, path, and code (integer) for each uri
     * @return UriMatcher for each of the possible matches (including NO_MATCH)
     */
    public static UriMatcher instanceUriMatcher(String[][] matches) {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        for (String[] match : matches) {
            uriMatcher.addURI(match[0], match[1], Integer.valueOf(match[2]));
        }
        return uriMatcher;
    }
}
