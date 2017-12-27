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
import com.newyear.christmas.fragment.adapter.HindiSMSAdapter
import com.newyear.christmas.fragment.model_hindi.Hindi_Example
import com.newyear.christmas.fragment.model_hindi.Hindi_Result
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ValidFragment")
class Fragment_3(val options:Int) : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {


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

        return  v;
    }
    //--------------------------------------------API---Work----------
    private fun jsonParse(option: Call<Hindi_Example>) {
        val  lists = ArrayList<Hindi_Result>()
        lists.clear()
        option.enqueue(object : Callback<Hindi_Example> {
            override fun onResponse(call: Call<Hindi_Example>?, response: Response<Hindi_Example>?) {
                if (response!!.isSuccessful) {
                    progressBar.visibility=View.GONE
                    swipeRefreshLayout.isRefreshing = false;

                    for(data: Hindi_Result in response.body().result!!){
                        lists.add(data)
                    }
                    val  adp1 = HindiSMSAdapter(lists,options)
                    recyclerView.adapter = adp1
                }
            }
            override fun onFailure(call: Call<Hindi_Example>?, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false;
                progressBar.visibility=View.GONE


            }

        });

    }
}