package newyear.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.newyear.R
import kotlinx.android.synthetic.main.main_horizontal_single_row.view.*
import newyear.pojos.Model

class MainAdapter(private val list: ArrayList<Model>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.main_horizontal_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: MainAdapter.ViewHolder?, position: Int) {

        holder!!.bindItem(list[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context:Context=itemView.context
        fun bindItem(model: Model) {
            Glide.with(context)
                    .load(model.avatar)
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into(itemView.image_view);
        }
    }
}