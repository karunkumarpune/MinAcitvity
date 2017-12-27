package com.newyear.christmas.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.adapter.EnglishSMSAdapter
import com.newyear.christmas.fragment.model_english.EnglishResult
import com.newyear.christmas.fragment.model_english.English_Example
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ValidFragment")
class Fragment_2(val options:Int) : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {


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

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var recyclerView: RecyclerView
    private var apiInterface: ApiInterface? = null
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiInterface = ApiUtils.getAPIService();
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v=inflater.inflate(R.layout.fragment_one, container, false);
        swipeRefreshLayout = v.findViewById<View>(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        progressBar =v.findViewById<View>(R.id.progressBar) as ProgressBar
        recyclerView=v.findViewById<View>(R.id.frag_recyclerView) as  RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(activity, LinearLayout.VERTICAL, false)



        swipeRefreshLayout.setOnRefreshListener(this);
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

        return  v;
    }
    //--------------------------------------------API---Work----------
    private fun jsonParse(option: Call<English_Example>) {
        val  lists = ArrayList<EnglishResult>()
        lists.clear()
         option.enqueue(object : Callback<English_Example> {
            override fun onResponse(call: Call<English_Example>?, response: Response<English_Example>?) {
                if (response!!.isSuccessful) {
                    progressBar.visibility=View.GONE
                    swipeRefreshLayout.isRefreshing = false;

                    for(data: EnglishResult in response.body().result!!){
                        lists.add(data)
                    }
                    val  adp1 = EnglishSMSAdapter(lists,options)
                    recyclerView.adapter = adp1
                }

            }
            override fun onFailure(call: Call<English_Example>?, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false;
                progressBar.visibility=View.GONE

            }

        });

    }
}