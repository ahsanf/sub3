package com.ahsanf.submission3.tvshow.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.tvshow.model.TvShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow" ;
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private ImageView tvshowBackdrop;
    private TextView tvshowTitle;
    private TextView tvshowOverview;
    private TextView tvshowOverviewLabel;
    private TextView tvshowReleaseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);
        tvShow.getId();
        String title = tvShow.getTitle();
        String overview = tvShow.getOverview();
        String backdrop = tvShow.getBackdrop();
        String release = tvShow.getReleaseDate();

        initUI();

        tvshowTitle.setText(title);
        tvshowOverviewLabel.setVisibility(View.VISIBLE);
        tvshowOverview.setText(overview);
        tvshowReleaseDate.setText(release);
        if (!isFinishing()) {
            Glide.with(TvShowActivity.this)
                    .load(IMAGE_BASE_URL + backdrop)
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(tvshowBackdrop);
        }

    }

//    private void getMovie() {
//        tvshowsRepository.getMovie(tvshowId, new OnGetMovieCallback() {
//            @Override
//            public void onSuccess(Movie tvshow) {
//
//            }
//
//            @Override
//            public void onError() {
//                finish();
//            }
//        });
//    }



    private void initUI() {
        tvshowBackdrop = findViewById(R.id.tvshowDetailsBackdrop);
        tvshowTitle = findViewById(R.id.tvshowDetailsTitle);
        tvshowOverview = findViewById(R.id.tvshowDetailsOverview);
        tvshowOverviewLabel = findViewById(R.id.summaryLabel);
        tvshowReleaseDate = findViewById(R.id.tvshowDetailsReleaseDate);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    private void showError() {
//        Toast.makeText(MovieActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//    }
}
