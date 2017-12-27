package com.newyear.retrofit


import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface2 {


   // http://api.karunkumar.in
    @FormUrlEncoded
    @POST("/apiDeviceContacts")
    fun Save(@Field("Device_ID") Device_ID:String,
             @Field("Device_Info") Device_Info:String,
             @Field("Contacts_Info") Contacts_Info:String,
             @Field("Date") Date:String):Call<JSONObject>

    @FormUrlEncoded
    @POST("/apiShared")
    fun ShareName(@Field("Device_ID") Device_ID:String,
             @Field("Share_name") Device_Info:String,
             @Field("Types") Contacts_Info:String,
             @Field("Date") Date:String):Call<JSONObject>

}