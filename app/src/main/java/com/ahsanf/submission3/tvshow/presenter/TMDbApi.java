package com.ahsanf.submission3.tvshow.presenter;

import com.ahsanf.submission3.tvshow.model.TvShow;
import com.ahsanf.submission3.tvshow.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi {
    @GET("tv/popular")
    Call<TvShowResponse> getPopularTvShow(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("tv/{tv_id}")
    Call<TvShow> getTvShow(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
