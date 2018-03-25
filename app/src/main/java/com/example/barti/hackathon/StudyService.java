package com.example.barti.hackathon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by barti on 25.11.17.
 */

public interface StudyService {
    @GET("studies")
    Call<List<Study>> listStudies(@Query("search") String search);
}
