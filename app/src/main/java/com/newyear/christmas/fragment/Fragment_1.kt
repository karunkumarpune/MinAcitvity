package com.newyear.christmas.fragment

import android.annotation.SuppressLint
import android.content.Context
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
import com.newyear.christmas.fragment.adapter.AvatarAdapter
import com.newyear.christmas.fragment.model_avatar.AvatarExample
import com.newyear.christmas.fragment.model_avatar.AvatarResult
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("ValidFragment")
class Fragment_1(val options:Int) : Fragment(), SwipeRefreshLayout.OnRefreshListener {

        override fun onRefresh() {


            when(options) {
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
        private lateinit var swipeRefreshLayout:SwipeRefreshLayout
        private lateinit var recyclerView: RecyclerView
         private var apiInterface: ApiInterface? = null
    private lateinit var progressBar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiInterface = ApiUtils.getAPIService();
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v=inflater.inflate(R.layout.fragment_one, container, false);
        progressBar =v.findViewById<View>(R.id.progressBar) as ProgressBar
        recyclerView=v.findViewById<View>(R.id.frag_recyclerView) as  RecyclerView
        swipeRefreshLayout = v.findViewById<View>(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        recyclerView.layoutManager= LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        swipeRefreshLayout.setOnRefreshListener(this);
         when(options) {
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

        return  v;
    }
    //--------------------------------------------API---Work----------




    private fun jsonParse(option: Call<AvatarExample>) {

        val  lists = ArrayList<AvatarResult>()
        lists.clear()
        option.enqueue(object : Callback<AvatarExample> {
            override fun onResponse(call: Call<AvatarExample>?, response: Response<AvatarExample>?) {
                if (response!!.isSuccessful) {
                    progressBar.visibility=View.GONE
                    swipeRefreshLayout.isRefreshing = false;

                    for(data:AvatarResult in response.body().result!!){
                       lists.add(data)
                    }
                    val  adp1 = context?.let { AvatarAdapter(it,lists,options) }
                    recyclerView.adapter = adp1
                }

            }
            override fun onFailure(call: Call<AvatarExample>?, t: Throwable?) {
                progressBar.visibility=View.GONE
                swipeRefreshLayout.isRefreshing = false;
            }

        });

    }
}