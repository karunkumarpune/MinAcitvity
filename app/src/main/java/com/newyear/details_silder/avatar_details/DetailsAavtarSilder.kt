package com.newyear.details_silder.avatar_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.model_avatar.AvatarExample
import com.newyear.christmas.fragment.model_avatar.AvatarResult
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import kotlinx.android.synthetic.main.activity_details_silder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsAavtarSilder : AppCompatActivity() {

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
        recyclerView = findViewById<View>(R.id.recycler_view_) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        select_position = intent.getIntExtra("select_position", 0)
        options = intent.getIntExtra("option", 0)



        when (options) {
            1 -> jsonParse(apiInterface!!.avatar1);
            2 -> jsonParse(apiInterface!!.avatar2);
            3 -> jsonParse(apiInterface!!.avatar3);
            4 -> jsonParse(apiInterface!!.avatar4);
            5 -> jsonParse(apiInterface!!.avatar5);
            6 -> jsonParse(apiInterface!!.avatar6);
            7 -> jsonParse(apiInterface!!.avatar7);
            8 -> jsonParse(apiInterface!!.avatar8);
            9 -> jsonParse(apiInterface!!.avatar9);
            10 -> jsonParse(apiInterface!!.avatar10);
        }

    }
    //--------------------------------------------API---Work----------
    private fun jsonParse(option: Call<AvatarExample>) {
        val  lists = ArrayList<AvatarResult>()
        lists.clear()
        option.enqueue(object : Callback<AvatarExample> {
            override fun onResponse(call: Call<AvatarExample>?, response: Response<AvatarExample>?) {
                if (response!!.isSuccessful) {
                    progressBar.visibility=View.GONE
                    for(data: AvatarResult in response.body().result!!){
                        lists.add(data)
                    }
                    val  adp1 = AavtarAdaptersSilder(lists)
                    recyclerView.adapter = adp1
                    recyclerView.scrollToPosition(select_position);
                    adp1.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<AvatarExample>?, t: Throwable?) {
            }

        });

    }
}