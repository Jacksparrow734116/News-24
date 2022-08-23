package com.example.news24;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
        // as we are calling data from array so we are calling
        // it with array list and naming that method as getAllCourses()
    Call<ResponseModel> Headlines(@Url String url);
    @GET
    Call<ResponseModel> CategoryNews(@Url String url);
}
