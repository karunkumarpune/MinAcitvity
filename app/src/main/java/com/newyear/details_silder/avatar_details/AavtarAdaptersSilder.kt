package com.newyear.details_silder.avatar_details

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.FileProvider
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.newyear.BuildConfig
import com.newyear.R
import com.newyear.christmas.fragment.model_avatar.AvatarResult
import com.newyear.retrofit.ApiInterface2
import com.newyear.retrofit.ApiUtils
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.adapter_details_slider.view.*
import kotlinx.android.synthetic.main.share_view_content.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class AavtarAdaptersSilder(private val list: ArrayList<AvatarResult>) : RecyclerView.Adapter<AavtarAdaptersSilder.ViewHolder>() {


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
        private val contexts: Context =itemView.context
        private var imageLoaders: ImageLoader? = null
        private var loadedImages: Bitmap? = null
        private var snackbar: Snackbar? = null
        private var apiInterface: ApiInterface2? = null



        init {
            this.imageLoaders = ImageLoader.getInstance()
            apiInterface = ApiUtils.getAPIService2();
        }

        //layout contentLayout
        private var coordinatorLayout: CoordinatorLayout = itemView.findViewById<View>(R.id.main_content) as CoordinatorLayout
        private var share_facebook: ImageButton = itemView.findViewById<View>(R.id.share_facebook) as ImageButton
        private var share_whatapp: ImageButton = itemView.findViewById<View>(R.id.share_whatapp) as ImageButton
        private var share_email: ImageButton = itemView.findViewById<View>(R.id.share_email) as ImageButton
        private var share_sms: ImageButton = itemView.findViewById<View>(R.id.share_sms) as ImageButton
        private var share_other: ImageButton = itemView.findViewById<View>(R.id.share_other) as ImageButton


        fun bindItem(model: AvatarResult) {

            itemView.thumbnail.visibility=View.VISIBLE
            itemView.tv_smss.visibility=View.GONE


            imageLoaders!!.loadImage(model.avatar, object : SimpleImageLoadingListener() {
                override fun onLoadingComplete(imageUri: String?, view: View?, loadedImagee: Bitmap?) {
                    itemView.thumbnail.setImageBitmap(loadedImagee)
                    loadedImages =getResizedBitmap(loadedImagee!!,400)
                } })


            share_facebook.setOnClickListener({
                val s=itemView.edit_name.text.toString()
               if(s == "") {
                   Validation()
               }else {
                   PostData(s, "Facebook")
                   ShareImage("com.facebook.katana", s, "facebook")
               }
            })
            share_whatapp.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else {
                    PostData(s, "Whatapp")
                    ShareImage("com.whatsapp", s, "Whatsapp")
                }
            })
            share_email.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else{
                    PostData(s,"Gmail")
                    ShareImage("com.google.android.gm", s, "Gmail")
                }
            })
            share_sms.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else{
                    PostData(s,"SMS")
                    ShareImage("com.android.mms", s, "SMS")
                }

            })

            share_other.setOnClickListener({
                val s=itemView.edit_name.text.toString()
                if(s == "") {
                    Validation()
                }else {
                    PostData(s, "Other")
                    try {
                        val file = File(contexts.externalCacheDir, "logicchip.png")
                        val fOut = FileOutputStream(file)
                        loadedImages!!.compress(Bitmap.CompressFormat.PNG, 100, fOut)
                        fOut.flush()
                        fOut.close()
                        file.setReadable(true, false)
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                        intent.type = "image/*"
                        contexts.startActivity(Intent.createChooser(intent, "Share image via"))

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } })
        }

        private fun PostData(name:String,type:String){
          val android_id = Settings.Secure.getString(contexts.contentResolver, Settings.Secure.ANDROID_ID);
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

        private fun Validation(){
            snackbar = Snackbar.make(this.coordinatorLayout!!, "Please enter name...", Snackbar.LENGTH_SHORT)
            val sbView = snackbar!!.view
            val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
            textView.setTextColor(Color.CYAN)
            snackbar!!.show()
        }

        private fun ShareImage(package_name:String, name:String, name_type:String){

            val sharedFile = createFile()
            val uri = FileProvider.getUriForFile(contexts, SHARED_PROVIDER_AUTHORITY, sharedFile)

            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "image/*"
            intent.`package` = package_name
            intent.putExtra(Intent.EXTRA_TEXT, name)
            try {
                contexts.startActivity(intent)
            } catch (ex: android.content.ActivityNotFoundException) {

                snackbar = Snackbar.make(this.coordinatorLayout!!, name_type + " have not been installed.", Snackbar.LENGTH_SHORT)
                val sbView = snackbar!!.view
                val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                textView.setTextColor(Color.YELLOW)
                snackbar!!.show()

            }

        }


        @Throws(IOException::class)
        private fun createFile(): File {

            val sharedFolder = File(contexts.filesDir, SHARED_FOLDER)
            sharedFolder.mkdirs()

            val sharedFile = File.createTempFile("picture", ".png", sharedFolder)
            sharedFile.createNewFile()

            writeBitmap(sharedFile, loadedImages!!)
            return sharedFile
        }
        companion object {

            private val SHARED_PROVIDER_AUTHORITY = BuildConfig.APPLICATION_ID + ".myfileprovider"
            private val SHARED_FOLDER = "shared"

            private fun writeBitmap(destination: File, bitmap: Bitmap) {
                var outputStream: FileOutputStream? = null
                try {
                    outputStream = FileOutputStream(destination)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    close(outputStream)
                }
            }

            private fun close(closeable: Closeable?) {
                if (closeable == null) return
                try {
                    closeable.close()
                } catch (ignored: IOException) {
                }
            }
        }

        fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
            var width = image.width
            var height = image.height

            val bitmapRatio = width.toFloat() / height.toFloat()
            if (bitmapRatio < 1 && width > maxSize) {

                width = maxSize
                height = (width / bitmapRatio).toInt()
            } else if (height > maxSize) {
                height = maxSize
                width = (height * bitmapRatio).toInt()
            }
            return Bitmap.createScaledBitmap(image, width, height, true)
        }
}
}