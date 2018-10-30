package com.example.macchhindra.realmdatabase;

import com.example.macchhindra.realmdatabase.module.MyModule;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GerritAPI {

    @GET("/posts")
    Call<List<MyModule>> getData();
}