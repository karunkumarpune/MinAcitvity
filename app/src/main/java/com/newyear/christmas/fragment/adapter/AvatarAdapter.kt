package com.newyear.christmas.fragment.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.newyear.R
import com.newyear.christmas.fragment.model_avatar.AvatarResult
import com.newyear.details_silder.avatar_details.DetailsAavtarSilder
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.adapter_fragment_row.view.*


class AvatarAdapter(context:Context, private val list: ArrayList<AvatarResult>,val option:Int) : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    private var imageLoader: ImageLoader? = null
    private var options_: DisplayImageOptions? = null

    private var options: Int = 0

    init {
        this.options = option

        imageLoader = ImageLoader.getInstance()
        imageLoader!!.init(ImageLoaderConfiguration.createDefault(context))
        options_ = DisplayImageOptions.Builder()
                 .showImageForEmptyUri(R.drawable.progress_animation)
                 .showImageOnFail(R.drawable.error_image)
               // .placeholder(R.drawable.progress_animation)
                .resetViewBeforeLoading()
                .cacheOnDisc()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(FadeInBitmapDisplayer(300))
                .build()

    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AvatarAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_fragment_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: AvatarAdapter.ViewHolder?, position: Int) {

        holder!!.bindItem(position,options)


        val pos = position % list.size
        val url = list[pos].avatar


        //----------------------------Count Progress Bar----------------------------------------------------------
        try {
            if (url!!.length > 5) {
                holder.image_view.visibility = View.VISIBLE
                loadImage(holder.image_view, url, holder.pb)
            } else {
                if (url == "NO") {
                    holder.image_view.visibility = View.INVISIBLE
                } else {
                    holder.image_view.visibility = View.GONE
                }
            }
        } catch (e: Exception) {
            //  Logger.show(e)
        }

    }

    private fun loadImage(imageView: ImageView?, loadURL: String, pb: ProgressBar?) {
        imageLoader!!.displayImage(loadURL, imageView, options_, object : SimpleImageLoadingListener() {
            override fun onLoadingStarted(imageUri: String, view: View) {
                pb!!.visibility = View.VISIBLE
            }

            override fun onLoadingFailed(imageUri: String, view: View, failReason: FailReason) {
                // var message: String? = null
                // when (failReason) {
                //    IO_ERROR -> message = "Input/Output error"
                //  OUT_OF_MEMORY -> message = "Out Of Memory error"
                // NETWORK_DENIED -> message = "Downloads are denied"
                // UNSUPPORTED_URI_SCHEME -> message = "Unsupported URI scheme"
                //  UNKNOWN -> message = "Unknown error"
                //      }
                //   Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            override fun onLoadingComplete(imageUri: String, view: View, loadedImage: Bitmap) {
                pb!!.visibility = View.INVISIBLE
            }
        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var pb: ProgressBar = itemView.findViewById<View>(R.id.pb) as ProgressBar
        internal var image_view: ImageView = itemView.findViewById<View>(R.id.image_view) as ImageView


        fun bindItem(select_position:Int,option:Int) {
            val context:Context=itemView.context
            itemView.image_view.visibility=View.VISIBLE
            itemView.tv_sms.visibility=View.GONE


            itemView.card_view_details.setOnClickListener({
                context.startActivity(Intent(context, DetailsAavtarSilder::class.java)
                        .putExtra("select_position",select_position)
                        .putExtra("option",option)
                 )
            })

        }
    }
}