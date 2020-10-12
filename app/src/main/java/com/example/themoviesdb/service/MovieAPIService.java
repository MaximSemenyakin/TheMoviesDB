package com.example.themoviesdb.service;

import com.example.themoviesdb.model.MovieAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIService {

    @GET("movie/popular")
    Call<MovieAPIResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieAPIResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);

}
