package com.ahsanf.submission3.tvshow.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahsanf.submission3.R;
import com.ahsanf.submission3.tvshow.model.TvShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private List<TvShow> tvshows;
    OnTvShowsClickCallback callback;

    public TvShowsAdapter(List<TvShow> tvshows, OnTvShowsClickCallback callback) {
        this.tvshows = tvshows;
        this.callback = callback;
    }



    @NonNull
    @Override
    public TvShowsAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsAdapter.TvShowViewHolder holder, int position) {
        holder.bind(tvshows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvshows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate, title;
        ImageView poster;
        TvShow tvshow;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.item_tvshow_release_date);
            title = itemView.findViewById(R.id.item_tvshow_title);
            poster = itemView.findViewById(R.id.item_tvshow_poster);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    callback.OnClick(tvshow);
                }
            });
        }

        public void bind(TvShow tvshow) {
            this.tvshow = tvshow;
            releaseDate.setText(tvshow.getReleaseDate());
            title.setText(tvshow.getTitle());
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + tvshow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(poster);
        }
    }

}
