package com.ahsanf.submission3.tvshow.presenter;

import com.ahsanf.submission3.tvshow.model.TvShow;

public interface OnGetTvShowCallback {
    void onSuccess(TvShow tvshow);

    void onError();
}
