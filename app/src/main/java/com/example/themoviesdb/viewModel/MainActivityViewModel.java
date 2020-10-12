package com.example.themoviesdb.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.themoviesdb.model.MovieDataSource;
import com.example.themoviesdb.model.MovieDataSourceFactory;
import com.example.themoviesdb.model.MovieRepository;
import com.example.themoviesdb.model.Result;
import com.example.themoviesdb.service.MovieAPIService;
import com.example.themoviesdb.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        MovieAPIService movieAPIService = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, movieAPIService);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(10).setPrefetchDistance(3).setPageSize(20).build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDataSourceFactory, config).setFetchExecutor(executor).build();
    }

    public LiveData<List<Result>> getAllMovieData() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
