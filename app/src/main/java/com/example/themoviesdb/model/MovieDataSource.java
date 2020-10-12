package com.example.themoviesdb.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.themoviesdb.R;
import com.example.themoviesdb.service.MovieAPIService;
import com.example.themoviesdb.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private MovieAPIService movieAPIService;
    private Application application;

    public MovieDataSource(MovieAPIService movieAPIService, Application application) {
        this.movieAPIService = movieAPIService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {
        movieAPIService = RetrofitInstance.getService();
        Call<MovieAPIResponse> call = movieAPIService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {
                MovieAPIResponse movieAPIResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieAPIResponse != null && movieAPIResponse.getResults() != null) {
                    results = (ArrayList<Result>) movieAPIResponse.getResults();
                    callback.onResult(results, null, (long)2);
                }
            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Result> callback) {
        movieAPIService = RetrofitInstance.getService();
        Call<MovieAPIResponse> call = movieAPIService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), params.key);
        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {
                MovieAPIResponse movieAPIResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieAPIResponse != null && movieAPIResponse.getResults() != null) {
                    results = (ArrayList<Result>) movieAPIResponse.getResults();
                    callback.onResult(results, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

            }
        });
    }
}
