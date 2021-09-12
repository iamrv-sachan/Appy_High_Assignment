package com.rajdroid.appyhighassignment.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajdroid.appyhighassignment.databinding.ShowListBinding
import com.rajdroid.appyhighassignment.entites.Article
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(val list: ArrayList<Article>, val clickListener: onitemClick) : RecyclerView.Adapter<viewholder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder1 {
        val binding: ShowListBinding = ShowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewholder1(binding)
    }

    override fun getItemCount(): Int {
        Log.i("info", "${list.size}")
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder1, position: Int) {
        holder.bind(list[position],position,clickListener)
    }

    interface onitemClick{
        fun onItemClicked(d: Int)
    }

}
class viewholder1(private val itemBinding: ShowListBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bind(data: Article,position: Int,clickListener: NewsAdapter.onitemClick) {

            if (data.urlToImage!=null){
                Glide.with(itemBinding.root).load(data.urlToImage)
                    .into(itemBinding.newsImage)
            }

        itemBinding.newsSource.text = data.author
        itemBinding.newsText.text = data.title
        itemBinding.show.setOnClickListener {
            clickListener.onItemClicked(position)

        }
    }

}


