package com.example.newsapp



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CardlayoutBinding


class RelatedNewsAdapter(
    private val context: Context,
    private val relatedNewsList: List<RelatedNewsItem>
) : RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedNewsViewHolder {
        val binding = CardlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RelatedNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelatedNewsViewHolder, position: Int) {
        val currentItem = relatedNewsList[position]
        holder.binding.apply {
            image.setImageResource(currentItem.imageResource)
            newsdescription.text = currentItem.description
            newstitle.text = currentItem.title
            root.setOnClickListener {
                // Create a new instance of the NewsdetailsPage fragment
                val fragment = NewsdetailsPage().apply {
                    // Pass data to the fragment using arguments bundle
                    arguments = Bundle().apply {
                        putString("Newstitle", currentItem.title)
                        putString("NewsDescription", currentItem.description)
                        putInt("NewsImage", currentItem.imageResource)
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

    override fun getItemCount(): Int = relatedNewsList.size

    inner class RelatedNewsViewHolder(val binding: CardlayoutBinding) : RecyclerView.ViewHolder(binding.root)
}