package com.velocikey.android.learning.cinebox2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link MovieDetailFragment}.
 */
public class MovieDetailActivity extends AppCompatActivity
        implements MovieDetailFragment.OnMovieDetailFragmentListener {
    // Class fields
    private static final String LOG_TAG = MovieDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "-->onCreate");
        setContentView(R.layout.activity_movie_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Show the Up button in the action bar.
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //

        Bundle extras = getIntent().getExtras();
        Log.v(LOG_TAG, "Extras count is " + extras.size());
        Log.v(LOG_TAG, " savedInstanceState null? " + (savedInstanceState == null));
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle(extras.size());
            arguments.putInt(MovieDetailFragment.ARG_movieId, getIntent().getIntExtra(MovieDetailFragment.ARG_movieId, -1));
            arguments.putString(MovieDetailFragment.ARG_title, getIntent().getStringExtra(MovieDetailFragment.ARG_title));
            arguments.putString(MovieDetailFragment.ARG_releaseDate, getIntent().getStringExtra(MovieDetailFragment.ARG_releaseDate));
            arguments.putFloat(MovieDetailFragment.ARG_popularity, getIntent().getFloatExtra(MovieDetailFragment.ARG_popularity, 0.0F));
            arguments.putFloat(MovieDetailFragment.ARG_rating, getIntent().getFloatExtra(MovieDetailFragment.ARG_rating, 0.0F));
            arguments.putString(MovieDetailFragment.ARG_overview, getIntent().getStringExtra(MovieDetailFragment.ARG_overview));
            arguments.putString(MovieDetailFragment.ARG_posterPath, getIntent().getStringExtra(MovieDetailFragment.ARG_posterPath));

            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Log.v(LOG_TAG, " in else with savedInstanceState not null");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MovieListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieDetailFragmentInteraction(String action) {
        Log.v(LOG_TAG, "-->onMovieDetailFragmentInteraction(action) " + action);
    }

    @Override
    public void onMovieDetailFragmentInteraction(int trailer, int review) {
        Log.v(LOG_TAG, "-->onMovieDetailFragmentInteraction(trailer, review) " + trailer + ", " + review);
    }
}
