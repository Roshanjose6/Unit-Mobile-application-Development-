package com.example.newsapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapp.databinding.CardlayoutlinearBinding

class DetailRelatedAdapter(
    private val context: Context,
    private val relatedNewsList: List<RelatedNewsItem>
) : RecyclerView.Adapter<DetailRelatedAdapter.DetailRelatedNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRelatedNewsViewHolder {
        val binding = CardlayoutlinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailRelatedNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailRelatedNewsViewHolder, position: Int) {
        val currentItem = relatedNewsList[position]
        holder.binding.apply {
            LimageView.setImageResource(currentItem.imageResource)
            Ldesc.text = currentItem.description
            Ltitle.text = currentItem.title

        }
    }

    override fun getItemCount(): Int = relatedNewsList.size

    inner class DetailRelatedNewsViewHolder(val binding: CardlayoutlinearBinding) : RecyclerView.ViewHolder(binding.root)
}