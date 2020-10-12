package com.example.themoviesdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themoviesdb.R;
import com.example.themoviesdb.model.Result;
import com.example.themoviesdb.view.MovieDetailsActivity;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        holder.titleTextView.setText(resultArrayList.get(position).getOriginalTitle());
        holder.popularityTextView.setText(Double.toString(resultArrayList.get(position).getPopularity()));
        String imagePath = "https://image.tmdb.org/t/p/w500/" + resultArrayList.get(position).getPosterPath();
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.progress_circle)
                .into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    private Context context;
    private ArrayList<Result> resultArrayList;

    public ResultAdapter(Context context, ArrayList<Result> resultArrayList) {
        this.context = context;
        this.resultArrayList = resultArrayList;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView popularityTextView;
        public ImageView movieImageView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            popularityTextView = itemView.findViewById(R.id.popularityTextView);
            movieImageView = itemView.findViewById(R.id.movieImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Result result = resultArrayList.get(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
