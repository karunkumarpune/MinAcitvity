package com.newyear.details_silder.hindi_sms_details

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.newyear.R
import com.newyear.christmas.fragment.model_hindi.Hindi_Result
import com.newyear.retrofit.ApiInterface2
import com.newyear.retrofit.ApiUtils
import kotlinx.android.synthetic.main.adapter_details_slider.view.*
import kotlinx.android.synthetic.main.share_view_content.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HindiAdapterSMS(private val list: ArrayList<Hindi_Result>) : RecyclerView.Adapter<HindiAdapterSMS.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_details_slider, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder!!.bindItem(list[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context =itemView.context
        private var snackbar: Snackbar? = null
        private var apiInterface: ApiInterface2? = null

        init {
            apiInterface = ApiUtils.getAPIService2();
        }
        //layout contentLayout
        private var coordinatorLayout: CoordinatorLayout = itemView.findViewById<View>(R.id.main_content) as CoordinatorLayout

        private var share_facebook: ImageButton = itemView.findViewById<View>(R.id.share_facebook) as ImageButton
        private var share_whatapp: ImageButton = itemView.findViewById<View>(R.id.share_whatapp) as ImageButton
        private var share_email: ImageButton = itemView.findViewById<View>(R.id.share_email) as ImageButton
        private var share_sms: ImageButton = itemView.findViewById<View>(R.id.share_sms) as ImageButton
        private var share_other: ImageButton = itemView.findViewById<View>(R.id.share_other) as ImageButton

        fun bindItem(model: Hindi_Result) {
            itemView.thumbnail.visibility=View.GONE
            itemView.tv_smss.visibility=View.VISIBLE
            itemView.tv_smss.text=model.smsHindi!!



            share_facebook.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else
                ShareText("com.facebook.lite",model.smsHindi!!,"facebook")
            })
            share_whatapp.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else
                ShareText("com.whatsapp",model.smsHindi!!,"Whatsapp")
            })
            share_email.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else
                ShareText("com.google.android.gm",model.smsHindi!!,"Gmail")
            })
            share_sms.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else
                ShareText("com.android.mms",model.smsHindi!!,"SMS")
            })


            share_other.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, model.smsHindi!!)
                    //  sendIntent.putExtra("MyKey", "This is second my text to send using my key.")
                    sendIntent.type = "text/plain"
                    context.startActivity(Intent.createChooser(sendIntent, "Send"))
                } })
        }

        private fun Validation(){
            snackbar = Snackbar.make(this.coordinatorLayout!!, "Please enter name...", Snackbar.LENGTH_SHORT)
            val sbView = snackbar!!.view
            val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
            textView.setTextColor(Color.CYAN)
            snackbar!!.show()
        }
        private fun PostData(name:String,type:String){
            val android_id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID);
            val  android_date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time);

            apiInterface!!.ShareName(android_id,name,type,android_date).enqueue(object:retrofit2.Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>?, response: Response<JSONObject>?) {
                    if(response!!.isSuccessful) {
                        Log.d("TAGS", ": - response : " +response.body().toString())
                    }
                }
                override fun onFailure(call: Call<JSONObject>?, t: Throwable?) {
                    Log.d("TAGS", ": - Error : " + t.toString())
                }

            })
        }

        private fun ShareText(package_name: String, name: String, name_type: String) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.`package` = package_name
            intent.putExtra(Intent.EXTRA_TEXT,name)
            try {
                context.startActivity(intent)
            } catch (ex: android.content.ActivityNotFoundException) {

                snackbar = Snackbar.make(this.coordinatorLayout!!, name_type + " have not been installed.", Snackbar.LENGTH_SHORT)
                val sbView = snackbar!!.view
                val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                textView.setTextColor(Color.YELLOW)
                snackbar!!.show()
            }
        }
    }
}