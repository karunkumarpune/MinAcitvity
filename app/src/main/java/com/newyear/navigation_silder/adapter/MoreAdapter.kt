package com.newyear.christmas.fragment.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.newyear.connectvity_check.Config
import com.newyear.R
import com.newyear.christmas.ChristmasActivity
import com.newyear.navigation_silder.pojo.Model2
import com.newyear.retrofit.model.Result
import kotlinx.android.synthetic.main.main__horizantal_layout.view.*
import newyear.adapter.MainAdapter
import newyear.pojos.Model
import org.json.JSONArray
import org.json.JSONObject

class MoreAdapter(val context: Context,val list:ArrayList<Model2>) : RecyclerView.Adapter<MoreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.main__horizantal_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 6
    }


    override fun onBindViewHolder(holder: MoreAdapter.ViewHolder?, position: Int) {
        holder!!.bindItem(position,list[0])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context:Context=itemView.context
        private var sp1:SharedPreferences = context.getSharedPreferences("nk1", Context.MODE_PRIVATE)
        private var sp2:SharedPreferences = context.getSharedPreferences("nk2", Context.MODE_PRIVATE)
        private var sp3:SharedPreferences = context.getSharedPreferences("nk3", Context.MODE_PRIVATE)
        private var sp4:SharedPreferences = context.getSharedPreferences("nk4", Context.MODE_PRIVATE)
        private var sp5:SharedPreferences = context.getSharedPreferences("nk5", Context.MODE_PRIVATE)
        private var sp6:SharedPreferences = context.getSharedPreferences("nk6", Context.MODE_PRIVATE)


        private fun loadJson1(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp1.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp1.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp1.getString("key", null)
                    Log.d("TAGS"," SP 1" +jsonText)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }

        private fun loadJson2(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp2.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp2.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp2.getString("key", null)
                    Log.d("TAGS"," SP 2" +jsonText)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }

        private fun loadJson3(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp3.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp3.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp3.getString("key", null)
                    Log.d("TAGS"," SP 1" +jsonText)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }

        private fun loadJson4(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp4.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp4.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp4.getString("key", null)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }

        private fun loadJson5(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp5.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp5.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp5.getString("key", null)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }

        private fun loadJson6(url:String){
            itemView.rec.layoutManager= LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            val  lists = ArrayList<String>()
            val  lists1 = ArrayList<Model>()
            lists.clear()
            AndroidNetworking.get(url).build().getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    val array = response!!.getJSONArray("result")
                    val edit1=sp6.edit()
                    edit1.clear()
                    edit1.commit()
                    val edit=sp6.edit()
                    for(i in 0..5){
                        val json=array.getJSONObject(i)
                        lists.add(json.getString("avatar"))
                        lists1.add(Model(json.getString("avatar")))
                    }
                    val textList = ArrayList<String>()
                    textList.addAll(lists)
                    val jsonText = Gson().toJson(textList)
                    edit.putString("key", jsonText)
                    edit.commit()
                    val  adp1 = MainAdapter(lists1)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                    val  lists11 = ArrayList<Model>()
                    lists11.clear()
                    val jsonText = sp6.getString("key", null)
                    val array= JSONArray(jsonText)
                    for(i in 0 until array.length()){
                        lists11.add(Model(array.get(i) as String))
                    }
                    val  adp1 = MainAdapter(lists11)
                    itemView.rec.adapter=adp1
                    adp1.notifyDataSetChanged()
                }
            })

        }



        fun bindItem(i:Int,list:Model2) {


                 // itemView.tv_title.text = list.festivalName1


                  when (i) {
                      0 -> {
                          itemView.tv_title.text = list.festivalName1
                          loadJson1(Config.BaseURL+"/"+list.avatar1)
                          Log.d("TAGS"," URL 1" +Config.BaseURL+"/"+list.avatar1)
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",1 ))
                          })
                      }
                      1 -> {
                          loadJson2(Config.BaseURL+"/"+list.avatar2)
                          Log.d("TAGS"," URL 2" +Config.BaseURL+"/"+list.avatar2)
                          itemView.tv_title.text = list.festivalName2
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",2 ))
                          })
                      }
                      2 -> {
                          loadJson3(Config.BaseURL+"/"+list.avatar3)
                          Log.d("TAGS"," URL 3" +Config.BaseURL+"/"+list.avatar3)
                          itemView.tv_title.text = list.festivalName3
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",3 ))
                          })
                      }
                      3 -> {
                          loadJson4(Config.BaseURL+"/"+list.avatar4)
                          Log.d("TAGS"," URL 4" +Config.BaseURL+"/"+list.avatar4)
                          itemView.tv_title.text = list.festivalName4
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",4 ))
                          })
                      }
                      4 -> {
                          loadJson5(Config.BaseURL+"/"+list.avatar5)
                          Log.d("TAGS"," URL 5" +Config.BaseURL+"/"+list.avatar5)
                          itemView.tv_title.text = list.festivalName5
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",5 ))
                          })
                      }
                      else -> {
                          loadJson6(Config.BaseURL+"/"+list.avatar6)
                          Log.d("TAGS"," URL 6" +Config.BaseURL+"/"+list.avatar6)
                          itemView.tv_title.text = list.festivalName6
                          itemView.tv_more.setOnClickListener({
                              context.startActivity(Intent(context, ChristmasActivity::class.java).putExtra("option",6 ))
                          })

                      }
                  }
              }
        }


}

