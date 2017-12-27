package com.newyear.details_silder.hindi_sms_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.model_english.EnglishResult
import com.newyear.christmas.fragment.model_english.English_Example
import com.newyear.christmas.fragment.model_hindi.Hindi_Example
import com.newyear.christmas.fragment.model_hindi.Hindi_Result
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import kotlinx.android.synthetic.main.activity_details_silder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsEnglishSMS_Silder : AppCompatActivity() {

    private lateinit var progressBar:ProgressBar
    private lateinit var recyclerView: RecyclerView
    private var apiInterface: ApiInterface? = null
    var select_position:Int = 0
    var options:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_silder)
        apiInterface = ApiUtils.getAPIService();

        btn_back_details.setOnClickListener({
            onBackPressed()
        })

        progressBar =findViewById<View>(R.id.progressBar) as ProgressBar
        recyclerView=findViewById<View>(R.id.recycler_view_) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        select_position=intent.getIntExtra("select_position",0)
        options = intent.getIntExtra("option", 0)




        when(options) {
            1 -> jsonParse(apiInterface!!.english1);
            2 -> jsonParse(apiInterface!!.english2);
            3 -> jsonParse(apiInterface!!.english3);
            4 -> jsonParse(apiInterface!!.english4);
            5 -> jsonParse(apiInterface!!.english5);
            6 -> jsonParse(apiInterface!!.english6);
            7 -> jsonParse(apiInterface!!.english7);
            8 -> jsonParse(apiInterface!!.english8);
            9 -> jsonParse(apiInterface!!.english9);
            10 -> jsonParse(apiInterface!!.english10);
        }
    }
    //--------------------------------------------API---Work----------
    private fun jsonParse(option: Call<English_Example>) {
        val  lists = ArrayList<EnglishResult>()
        lists.clear()
        option.enqueue(object : Callback<English_Example> {
            override fun onResponse(call: Call<English_Example>?, response: Response<English_Example>?) {
                if (response!!.isSuccessful) {
                    progressBar.visibility=View.GONE
                    for(data: EnglishResult in response.body().result!!){
                        lists.add(data)
                    }
                    val  adp1 = EnglishAdapterSMS(lists)
                    recyclerView.adapter = adp1
                    recyclerView.scrollToPosition(select_position);
                    adp1.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<English_Example>?, t: Throwable?) {
            }

        });

    }
}