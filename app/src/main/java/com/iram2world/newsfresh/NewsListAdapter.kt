package com.iram2world.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener :NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private val item:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
          listener.onItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }
    override fun getItemCount(): Int {
        return  item.size
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
        holder.description.text = currentItem.des
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
   fun updatedNews(updatedNews :ArrayList<News>)
   {
       item.clear()
       item.addAll(updatedNews)
       notifyDataSetChanged()
   }

}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val titleView: TextView = itemView.findViewById(R.id.title)
    val description: TextView = itemView.findViewById(R.id.des)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author :TextView = itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun onItemClicked (item:News)
}