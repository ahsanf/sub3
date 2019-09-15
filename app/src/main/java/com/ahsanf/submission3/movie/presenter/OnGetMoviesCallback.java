package com.ahsanf.submission3.movie.presenter;

import com.ahsanf.submission3.movie.model.Movie;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movie> movies);

    void onError();
}
