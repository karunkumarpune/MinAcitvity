package com.newyear.details_silder.hindi_sms_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.model_hindi.Hindi_Example
import com.newyear.christmas.fragment.model_hindi.Hindi_Result
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import kotlinx.android.synthetic.main.activity_details_silder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsHindiSMS_Silder : AppCompatActivity() {

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
            1 -> jsonParse(apiInterface!!.hindi1);
            2 -> jsonParse(apiInterface!!.hindi2);
            3 -> jsonParse(apiInterface!!.hindi3);
            4 -> jsonParse(apiInterface!!.hindi4);
            5 -> jsonParse(apiInterface!!.hindi5);
            6 -> jsonParse(apiInterface!!.hindi6);
            7 -> jsonParse(apiInterface!!.hindi7);
            8 -> jsonParse(apiInterface!!.hindi8);
            9 -> jsonParse(apiInterface!!.hindi9);
            10 -> jsonParse(apiInterface!!.hindi10);
        }

    }
    //--------------------------------------------API---Work----------
    private fun jsonParse(option: Call<Hindi_Example>) {
        val  lists = ArrayList<Hindi_Result>()
        lists.clear()
        option.enqueue(object : Callback<Hindi_Example> {
            override fun onResponse(call: Call<Hindi_Example>?, response: Response<Hindi_Example>?) {
                if (response!!.isSuccessful){
                    progressBar.visibility=View.GONE
                for(data: Hindi_Result in response.body().result!!){
                        lists.add(data)
                    }
                    val  adp1 = HindiAdapterSMS(lists)
                    recyclerView.adapter = adp1
                    recyclerView.scrollToPosition(select_position);
                    adp1.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<Hindi_Example>?, t: Throwable?) {
            }

        });

    }
}