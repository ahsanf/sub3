package com.ahsanf.submission3.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.movie.model.Movie;
import com.ahsanf.submission3.movie.presenter.MoviesAdapter;
import com.ahsanf.submission3.movie.presenter.MoviesRepository;
import com.ahsanf.submission3.movie.presenter.OnGetMoviesCallback;
import com.ahsanf.submission3.movie.presenter.OnMoviesClickCallback;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class FragmentMovie extends Fragment {
    private MoviesRepository moviesRepository;
    private RecyclerView moviesList;
    private MoviesAdapter adapter;
    ProgressBar progressBar;
    public static ArrayList<Movie> dataMovie= new ArrayList<>();

    public static FragmentMovie getInstance(){
        return new FragmentMovie();
    }

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
    public void onViewCreated(@NonNull View view,final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesRepository = MoviesRepository.getInstance();
        moviesList = view.findViewById(R.id.movies_list);
        progressBar = view.findViewById(R.id.progressBar1);
        moviesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter= new MoviesAdapter(dataMovie,callback);
        moviesList.setAdapter(adapter);
        if(null == savedInstanceState) {
            progressBar.setVisibility(View.VISIBLE);
            moviesRepository.getMovies(new OnGetMoviesCallback() {
                @Override
                public void onSuccess(List<Movie> movies) {
                        dataMovie.clear();
                    Log.d(this.getClass().getSimpleName(),"here "+movies.size() );
                        dataMovie.addAll(movies);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(GONE);
                }
                @Override
                public void onError() {
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(GONE);
                }
            });

        }
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
