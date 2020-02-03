package ru.asshands.softwire.instagirl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GirlsAdapter : RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder>() {

    private var girls: List<Girl> = emptyList()

    fun setGirls(girls: List<Girl>){
        this.girls = girls
        notifyDataSetChanged()
    }

    class GirlsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.item_girl_name)
        private val photo: ImageView = itemView.findViewById(R.id.item_girl_photo)
        private val likes: TextView = itemView.findViewById(R.id.item_girl_likes)


        fun bind(girl:Girl){
            name.text = girl.name
            likes.text = "Нравится: ${girl.likes}"

            Picasso.get().load(girl.photo).into(photo)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_girl, parent, false)
        return GirlsViewHolder(view)
    }

    override fun getItemCount(): Int = girls.size

    override fun onBindViewHolder(holder: GirlsViewHolder, position: Int) {
        holder.bind(girls[position])
    }

}