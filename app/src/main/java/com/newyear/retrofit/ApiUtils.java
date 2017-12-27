package com.newyear.retrofit;

public class ApiUtils {
 
   public static final String BASE_URL1 ="https://raw.githubusercontent.com";
   public static final String BASE_URL2 = "http://api.karunkumar.in";

    public static ApiInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL1).create(ApiInterface.class);
    }

    public static ApiInterface2 getAPIService2() {
        return RetrofitClient2.getClient(BASE_URL2).create(ApiInterface2.class);
    }
}