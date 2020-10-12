package com.example.themoviesdb.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.themoviesdb.R;
import com.example.themoviesdb.service.MovieAPIService;
import com.example.themoviesdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {
        MovieAPIService movieAPIService = RetrofitInstance.getService();

        Call<MovieAPIResponse> call = movieAPIService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {
                MovieAPIResponse movieAPIResponse = response.body();

                if (movieAPIResponse != null && movieAPIResponse.getResults() != null) {
                    results = (ArrayList<Result>) movieAPIResponse.getResults();
                    mutableLiveData.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
