package com.newyear.christmas.fragment.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.model_english.EnglishResult
import com.newyear.details_silder.avatar_details.DetailsAavtarSilder
import com.newyear.details_silder.hindi_sms_details.DetailsEnglishSMS_Silder
import kotlinx.android.synthetic.main.adapter_fragment_row.view.*

class EnglishSMSAdapter(private val list: ArrayList<EnglishResult>,val option:Int) : RecyclerView.Adapter<EnglishSMSAdapter.ViewHolder>() {

    private var options:Int=0
    init{
        this.options=option
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EnglishSMSAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_fragment_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: EnglishSMSAdapter.ViewHolder?, position: Int) {

        holder!!.bindItem(list[position],position,options)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context =itemView.context
        internal var pb: ProgressBar = itemView.findViewById<View>(R.id.pb) as ProgressBar


        fun bindItem(model: EnglishResult,select_position:Int,option:Int){
            itemView.image_view.visibility=View.GONE
            pb.visibility= View.GONE
            itemView.tv_sms.visibility=View.VISIBLE
            itemView.tv_sms.text=model.smsEnglish

            itemView.card_view_details.setOnClickListener({
                context.startActivity(Intent(context, DetailsEnglishSMS_Silder::class.java)
                        .putExtra("select_position",select_position)
                        .putExtra("option",option)
                )
            })
        }
    }
}
