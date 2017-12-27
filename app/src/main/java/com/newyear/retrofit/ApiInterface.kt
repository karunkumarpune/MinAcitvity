package com.newyear.retrofit


import com.newyear.christmas.fragment.model_avatar.AvatarExample
import com.newyear.christmas.fragment.model_english.English_Example
import com.newyear.christmas.fragment.model_hindi.Hindi_Example
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

   /* @get:GET("/karunkumarpune/HappyNewYear/master/home_api")
    val answers: Call<Example>
*/

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_1.json")
    val avatar1: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_2.json")
    val avatar2: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_3.json")
    val avatar3: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_4.json")
    val avatar4: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_5.json")
    val avatar5: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_6.json")
    val avatar6: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_7.json")
    val avatar7: Call<AvatarExample>


    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_8.json")
    val avatar8: Call<AvatarExample>

    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_9.json")
    val avatar9: Call<AvatarExample>


    @get:GET("/karunkumarpune/HappyNewYear/master/avatar_10.json")
    val avatar10: Call<AvatarExample>


    //-----------------------------------------English---------------
    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms1.json")
    val english1: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms2.json")
    val english2: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms3.json")
    val english3: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms4.json")
    val english4: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms5.json")
    val english5: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms6.json")
    val english6: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms7.json")
    val english7: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms8.json")
    val english8: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms9.json")
    val english9: Call<English_Example>


    @get:GET("/karunkumarpune/HappyNewYear/master/english_sms10.json")
    val english10: Call<English_Example>


    //-------------------------Hindi------------

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms1.json")
    val hindi1: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms2.json")
    val hindi2: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms3.json")
    val hindi3: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms4.json")
    val hindi4: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms5.json")
    val hindi5: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms6.json")
    val hindi6: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms7.json")
    val hindi7: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms8.json")
    val hindi8: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms9.json")
    val hindi9: Call<Hindi_Example>

    @get:GET("/karunkumarpune/HappyNewYear/master/hindi_sms10.json")
    val hindi10: Call<Hindi_Example>


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