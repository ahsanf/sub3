package com.ahsanf.submission3.tvshow.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.tvshow.presenter.TvShowRepository;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TvShowActivity extends AppCompatActivity {
    public static String TVSHOW_ID = "tvshow_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    public static String TVSHOW_TITLE = "tvshow_title";
    public static String TVSHOW_OVERVIEW = "tvshow_overview";
    public static String TVSHOW_BACKDROP= "tvshow_backdrop";
    public static String TVSHOW_POSTER= "tvshow_poster";
    public static String TVSHOW_RELEASE= "tvshow_release";


    private ImageView tvshowBackdrop;
    private TextView tvshowTitle;
    private TextView tvshowOverview;
    private TextView tvshowOverviewLabel;
    private TextView tvshowReleaseDate;

    private TvShowRepository tvshowsRepository;
    private int tvshowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show);

        tvshowId = getIntent().getIntExtra(TVSHOW_ID, tvshowId);
        final String title = getIntent().getExtras().getString(TVSHOW_TITLE);
        final String overview = getIntent().getExtras().getString(TVSHOW_OVERVIEW);
        final String backdrop = getIntent().getExtras().getString(TVSHOW_BACKDROP);
        final String release = getIntent().getExtras().getString(TVSHOW_RELEASE);
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
