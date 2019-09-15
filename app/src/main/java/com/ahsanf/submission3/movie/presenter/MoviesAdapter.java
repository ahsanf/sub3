package com.ahsanf.submission3.movie.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.movie.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private OnMoviesClickCallback callback;


    public MoviesAdapter(List<Movie> movies, OnMoviesClickCallback callback) {
        this.movies = movies;
        this.callback = callback;
    }


    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        Movie movie;
        TextView releaseDate;
        TextView title;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.item_movie_release_date);
            title = itemView.findViewById(R.id.item_movie_title);
            poster = itemView.findViewById(R.id.item_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(movie);
                }
            });
        }

        public void bind(final Movie movie) {
            this.movie = movie;
            releaseDate.setText(movie.getReleaseDate());
            title.setText(movie.getTitle());
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(poster);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override     public void onClick(View v) {
////                    Intent intent = new Intent(v.getContext(), MovieActivity.class);
////                    intent.putExtra(MovieActivity.MOVIE_ID, movie.getId());
////                    v.getContext().startActivity(intent);
////                }
//
//            });

        }


    }



}
