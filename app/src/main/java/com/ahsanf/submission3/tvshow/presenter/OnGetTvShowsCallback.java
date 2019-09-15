package com.ahsanf.submission3.tvshow.presenter;

import com.ahsanf.submission3.tvshow.model.TvShow;

import java.util.List;

public interface OnGetTvShowsCallback {
    void onSuccess(List<TvShow> tvshows);

    void onError();
}
