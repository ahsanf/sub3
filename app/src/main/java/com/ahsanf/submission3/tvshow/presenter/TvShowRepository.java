package com.ahsanf.submission3.tvshow.presenter;

import com.ahsanf.submission3.tvshow.model.TvShow;
import com.ahsanf.submission3.tvshow.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowRepository {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";
    private static TvShowRepository repository;
    private TMDbApi api;

    public TvShowRepository(TMDbApi api) {
        this.api = api;
    }

    public static TvShowRepository getInstance(){
        if(repository == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new TvShowRepository(retrofit.create(TMDbApi.class));
        }
        return repository;
    }

    public void getTvShows(final OnGetTvShowsCallback callback){
        api.getPopularTvShow("e2690ff4cd80ae035db3c60030cccc0c", LANGUAGE,1)
                .enqueue(new Callback<TvShowResponse>() {
                    @Override
                    public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                        if(response.isSuccessful()){
                            TvShowResponse tvShowResponse = response.body();
                            callback.onSuccess(tvShowResponse.getTvShows());
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TvShowResponse> call, Throwable throwable) {
                        callback.onError();
                    }
                });

    }

    public void getTvShow(int tvshowId, final OnGetTvShowCallback callback){
        api.getTvShow(tvshowId, "e2690ff4cd80ae035db3c60030cccc0c", LANGUAGE)
                .enqueue(new Callback<TvShow>() {
                    @Override
                    public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                        if (response.isSuccessful()){
                            TvShow tvshow = response.body();
                            if (tvshow != null){
                                callback.onSuccess(tvshow);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TvShow> call, Throwable throwable) {
                        callback.onError();
                    }
                });
    }
}
