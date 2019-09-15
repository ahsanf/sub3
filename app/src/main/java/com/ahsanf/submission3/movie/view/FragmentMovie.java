package com.ahsanf.submission3.movie.view;

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
import android.widget.Toast;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.movie.presenter.MoviesAdapter;
import com.ahsanf.submission3.movie.presenter.MoviesRepository;
import com.ahsanf.submission3.movie.presenter.OnGetMoviesCallback;
import com.ahsanf.submission3.movie.presenter.OnMoviesClickCallback;
import com.ahsanf.submission3.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class FragmentMovie extends Fragment {
    private ArrayList<Movie> movies =new ArrayList<>();
    private MoviesRepository moviesRepository;
    private RecyclerView moviesList;
    private MoviesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesRepository = MoviesRepository.getInstance();
        moviesList = view.findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                if (adapter == null) {
                    adapter = new MoviesAdapter(movies, callback);
                    moviesList.setAdapter(adapter);
                } else {

                }
            }
            @Override
            public void onError() {
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

    }
    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
        @Override
        public void onClick(Movie movie) {
            Intent intent = new Intent(getActivity(), MovieActivity.class);
            intent.putExtra(MovieActivity.MOVIE_ID, movie.getId());
            intent.putExtra(MovieActivity.MOVIE_TITLE, movie.getTitle());
            intent.putExtra(MovieActivity.MOVIE_BACKDROP, movie.getBackdrop());
            intent.putExtra(MovieActivity.MOVIE_OVERVIEW, movie.getOverview());
            intent.putExtra(MovieActivity.MOVIE_POSTER, movie.getPosterPath());
            intent.putExtra(MovieActivity.MOVIE_RELEASE, movie.getReleaseDate());
            startActivity(intent);

        }
    };

}
