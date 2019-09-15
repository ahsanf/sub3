package com.ahsanf.submission3.movie.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ahsanf.submission3.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieActivity extends AppCompatActivity {
    public static String MOVIE_ID = "movie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    public static String MOVIE_TITLE = "movie_title";
    public static String MOVIE_OVERVIEW = "movie_overview";
    public static String MOVIE_BACKDROP= "movie_backdrop";
    public static String MOVIE_POSTER= "movie_poster";
    public static String MOVIE_RELEASE= "movie_release";
    private ImageView movieBackdrop;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView movieOverviewLabel;
    private TextView movieReleaseDate;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);
        final String title = getIntent().getExtras().getString(MOVIE_TITLE);
        final String overview = getIntent().getExtras().getString(MOVIE_OVERVIEW);
        final String backdrop = getIntent().getExtras().getString(MOVIE_BACKDROP);
        final String release = getIntent().getExtras().getString(MOVIE_RELEASE);
        initUI();

        movieTitle.setText(title);
        movieOverviewLabel.setVisibility(View.VISIBLE);
        movieOverview.setText(overview);
        movieReleaseDate.setText(release);
        if (!isFinishing()) {
            Glide.with(MovieActivity.this)
                    .load(IMAGE_BASE_URL + backdrop)
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(movieBackdrop);
        }

    }

    private void initUI() {
        movieBackdrop = findViewById(R.id.movieDetailsBackdrop);
        movieTitle = findViewById(R.id.movieDetailsTitle);
        movieOverview = findViewById(R.id.movieDetailsOverview);
        movieOverviewLabel = findViewById(R.id.summaryLabel);
        movieReleaseDate = findViewById(R.id.movieDetailsReleaseDate);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
