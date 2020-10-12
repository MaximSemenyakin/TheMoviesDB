package com.example.themoviesdb.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.themoviesdb.service.MovieAPIService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private Application application;
    private MovieAPIService movieAPIService;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(Application application, MovieAPIService movieAPIService) {
        this.application = application;
        this.movieAPIService = movieAPIService;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieAPIService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
