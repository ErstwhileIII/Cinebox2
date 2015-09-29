package com.velocikey.android.learning.cinebox2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.velocikey.android.learning.cinebox2.webinfo.movie.MovieInfo;


/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MovieListFragment} and the item details
 * (if present) is a {@link MovieDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link com.velocikey.android.learning.cinebox2.MovieListFragment.OnMovieListFragmentListener} interface
 * to listen for item selections.
 */
public class MovieListActivity extends AppCompatActivity
        implements MovieListFragment.OnMovieListFragmentListener {
    // Class fields
    private static final String LOG_TAG = MovieListActivity.class.getSimpleName();
    // Object fields
    MovieInfo mMovieInfo = null;

    /**
     * Is the activity runnng where two panes are visible?
     * This could be for a tablet device, or for a phone in landscape mode where sufficient
     * space is present.
     */
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "-->onCreate");
        setContentView(R.layout.activity_movie_list);

//        setContentView(R.layout.activity_movie_app_bar);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // The detail container view will be present only in the
        // large-screen layouts (res/values-large and
        // res/values-sw600dp). If this view is present, then the
        // activity should be in two-pane mode.
        isTwoPane = findViewById(R.id.movie_detail_container) != null;

        if (isTwoPane) {
            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            //TODO switch to cinebox movie list
//            ((MovieListFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.movie_list))
//                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "-->onRestoreInstanceState");
        //TODO need for device rotation?
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(LOG_TAG, "-->onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.v(LOG_TAG, "-->onOptionsItemSelected (" + id + ")");
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "-->onResume");
        //TODO need to handle getMovies?
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "-->onSaveInstanceState (" + savedInstanceState.size() + ")");
        setMovieDetailArguments(savedInstanceState, mMovieInfo);
    }

    /**
     * @param args
     */
    private void setMovieDetailArguments(Bundle args, MovieInfo movieInfo) {
        if (movieInfo != null) {
            args.putInt(MovieDetailFragment.ARG_movieId, movieInfo.getMovieId());
            args.putString(MovieDetailFragment.ARG_title, movieInfo.getTitle());
            args.putString(MovieDetailFragment.ARG_releaseDate, movieInfo.getReleaseDate());
            args.putFloat(MovieDetailFragment.ARG_popularity, movieInfo.getPopularity());
            args.putFloat(MovieDetailFragment.ARG_rating, movieInfo.getRating());
            args.putFloat(MovieDetailFragment.ARG_favorite, movieInfo.getFavorite());
            args.putString(MovieDetailFragment.ARG_overview, movieInfo.getOverview());
            args.putString(MovieDetailFragment.ARG_posterPath, movieInfo.getFullPosterPath(300));
        }
    }

    // Methods called from fragments
    @Override
    public void onMovieListFragmentInteraction(MovieInfo movieInfo) {
        mMovieInfo = movieInfo;
        Log.v(LOG_TAG, "-->onMovieListFragmentInteraction");
        Log.v(LOG_TAG, " .. " + movieInfo.getTitle());

        Bundle args = new Bundle();
        setMovieDetailArguments(args, mMovieInfo);

        if (isTwoPane) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, movieDetailFragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(this, MovieDetailActivity.class);
            detailIntent.putExtras(args);
            startActivity(detailIntent);
        }
    }

}
