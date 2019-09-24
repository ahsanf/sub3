package com.ahsanf.submission3.tvshow.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.tvshow.model.TvShow;
import com.ahsanf.submission3.tvshow.presenter.OnGetTvShowsCallback;
import com.ahsanf.submission3.tvshow.presenter.OnTvShowsClickCallback;
import com.ahsanf.submission3.tvshow.presenter.TvShowRepository;
import com.ahsanf.submission3.tvshow.presenter.TvShowsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentTvShow extends Fragment {
    private TvShowRepository tvShowRepository;
    private RecyclerView tvshowList;
    private TvShowsAdapter adapter;
    ProgressBar progressBar;
    private static ArrayList<TvShow> dataTvShow = new ArrayList<>();

    public static FragmentTvShow getInstance(){
        return new FragmentTvShow();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvShowRepository = TvShowRepository.getInstance();
        tvshowList = view.findViewById(R.id.tvshow_list);
        tvshowList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TvShowsAdapter(dataTvShow, callback);
        progressBar = view.findViewById(R.id.progressBar2);
        tvshowList.setAdapter(adapter);
        if (null == savedInstanceState){
            progressBar.setVisibility(View.VISIBLE);
            tvShowRepository.getTvShows(new OnGetTvShowsCallback() {
                @Override
                public void onSuccess(List<TvShow> tvshows) {
                    dataTvShow.clear();
                    dataTvShow.addAll(tvshows);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }
                @Override
                public void onError() {
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        }

    }

    OnTvShowsClickCallback callback = new OnTvShowsClickCallback() {
        @Override
        public void OnClick(TvShow tvshow) {
            Intent intent = new Intent(getActivity(), TvShowActivity.class);
            intent.putExtra(TvShowActivity.TVSHOW_ID, tvshow.getId());
            intent.putExtra(TvShowActivity.TVSHOW_TITLE, tvshow.getTitle());
            intent.putExtra(TvShowActivity.TVSHOW_BACKDROP, tvshow.getBackdrop());
            intent.putExtra(TvShowActivity.TVSHOW_OVERVIEW, tvshow.getOverview());
            intent.putExtra(TvShowActivity.TVSHOW_POSTER, tvshow.getPosterPath());
            intent.putExtra(TvShowActivity.TVSHOW_RELEASE, tvshow.getReleaseDate());
            startActivity(intent);
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}

