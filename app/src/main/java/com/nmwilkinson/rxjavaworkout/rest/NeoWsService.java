package com.nmwilkinson.rxjavaworkout.rest;

import com.nmwilkinson.rxjavaworkout.model.AsteroidDetails;
import com.nmwilkinson.rxjavaworkout.model.Asteroids;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neil on 24/03/2016.
 */
public interface NeoWsService
{
    @GET("/neo/rest/v1/feed")
    Observable<Asteroids> listAsteroids(@Query("start_date") String startDate, @Query("end_date") String endDate, @Query("api_key") String apiKey);

    //https://api.nasa.gov/neo/rest/v1/neo/3542519?api_key=DEMO_KEY
    @GET("/neo/rest/v1/neo/{asteroid_id}")
    Observable<AsteroidDetails> getAsteroidDetails(@Path("asteroid_id") String asteroidId, @Query("api_key") String apiKey);
}
