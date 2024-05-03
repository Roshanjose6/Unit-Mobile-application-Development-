package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapp.databinding.CardlayoutBinding

class TopNewsAdapter(private val context: Context, private val topNewsList: List<TopNewsItem>) : RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        val binding = CardlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        val currentItem = topNewsList[position]
        holder.binding.apply {
            image.setImageResource(currentItem.TopimageResource)
            newsdescription.text = currentItem.Topdescription
            newstitle.text = currentItem.Toptitle
            root.setOnClickListener {
                // Create a new instance of the NewsdetailsPage fragment
                val fragment = NewsdetailsPage().apply {
                    // Pass data to the fragment using arguments bundle
                    arguments = Bundle().apply {
                        putString("Newstitle", currentItem.Toptitle)
                        putString("NewsDescription", currentItem.Topdescription)
                        putInt("NewsImage", currentItem.TopimageResource)
                    }
                }

                // Replace the current fragment with NewsdetailsPage fragment
                val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.Mainframelayout, fragment)
                transaction.addToBackStack(null) // Optional: Add fragment to back stack
                transaction.commit()
            }
        }
    }

    override fun getItemCount(): Int = topNewsList.size
    inner class TopNewsViewHolder(val binding: CardlayoutBinding) : RecyclerView.ViewHolder(binding.root)
}