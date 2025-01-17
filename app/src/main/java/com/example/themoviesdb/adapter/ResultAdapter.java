package com.example.themoviesdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themoviesdb.R;
import com.example.themoviesdb.databinding.ResultListItemBinding;
import com.example.themoviesdb.model.Result;
import com.example.themoviesdb.view.MovieDetailsActivity;

import java.util.ArrayList;

public class ResultAdapter extends PagedListAdapter<Result, ResultAdapter.ResultViewHolder> {

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ResultListItemBinding resultListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.result_list_item, parent, false);

        return new ResultViewHolder(resultListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = getItem(position);


        holder.resultListItemBinding.setResult(result);
    }


    private Context context;

    public ResultAdapter(Context context) {
        super(Result.CALLBACK);
        this.context = context;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private ResultListItemBinding resultListItemBinding;

        public ResultViewHolder(@NonNull ResultListItemBinding resultListItemBinding) {
            super(resultListItemBinding.getRoot());
            this.resultListItemBinding = resultListItemBinding;
            resultListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Result result = getItem(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
