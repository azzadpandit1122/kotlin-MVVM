package com.example.myapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.RvclasslistRecyclerLayoutBinding
import com.example.myapplication.mdel.MovieResponse
import com.example.myapplication.mdel.Search

class classListSingUpAdapter(private var classModel: ArrayList<Search>) : RecyclerView.Adapter<classListSingUpAdapter.ClassViewHolder>() {

  private lateinit var binding: RvclasslistRecyclerLayoutBinding
    var row_index :Int = -1
    inner class ClassViewHolder(private val binding: RvclasslistRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(largeNews: Search) {
            binding.title.text = largeNews.Title+"\n"+largeNews.Type+"\n"+largeNews.Year+"\n"+largeNews.imdbID
            Glide.with(binding.root.context).load(largeNews.Poster.toString()).into(binding.poster);

            binding.root.setOnClickListener {
                row_index = position
                notifyDataSetChanged()
                (binding.root.context as MainActivity).clickListnerAdapter(largeNews.Title)
            }
            if (row_index==position){
               binding.root.setBackgroundColor(Color.BLUE)
          }else{
          binding.root.setBackgroundColor(Color.WHITE)
          }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding = RvclasslistRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classModel = classModel[position]
        holder.bind(classModel)
    }

    override fun getItemCount(): Int {
        return classModel.size
    }

    fun filterList(filteredList: java.util.ArrayList<Search>) {
        this.classModel = filteredList
        notifyDataSetChanged()
    }
}