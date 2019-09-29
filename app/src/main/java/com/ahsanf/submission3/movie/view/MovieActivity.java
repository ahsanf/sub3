package com.ahsanf.submission3.movie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.movie.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie" ;
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private ImageView movieBackdrop;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView movieOverviewLabel;
    private TextView movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        movie.getId();
        String title = movie.getTitle();
        String overview = movie.getOverview();
        String backdrop = movie.getBackdrop();
        String release = movie.getReleaseDate();
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
