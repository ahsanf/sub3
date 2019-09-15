package com.ahsanf.submission3.movie.presenter;

import com.ahsanf.submission3.movie.model.Movie;

public interface OnGetMovieCallback {
    void onSuccess(Movie movie);

    void onError();
}
