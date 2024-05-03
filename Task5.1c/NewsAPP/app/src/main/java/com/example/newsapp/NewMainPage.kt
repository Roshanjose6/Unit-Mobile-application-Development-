package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewMainPageBinding

class NewMainPage : Fragment() {
    private lateinit var binding: FragmentNewMainPageBinding

    private lateinit var topNewsAdapter: TopNewsAdapter
    private lateinit var relatedNewsAdapter: RelatedNewsAdapter
    private val topNewsList = listOf(
        TopNewsItem("Scientists Discover New Species of Deep-Sea Fish", "Researchers have identified a previously unknown species of fish living in the depths of the Pacific Ocean. This discovery sheds light on the biodiversity of deep-sea ecosystems", R.drawable.a),
        TopNewsItem("Global Leaders Gather for Climate Summit in Paris", "World leaders convene in Paris to discuss urgent measures to combat climate change. The summit aims to strengthen international cooperation and accelerate efforts to reduce greenhouse gas emissions.", R.drawable.b),
        TopNewsItem("Tech Giant Unveils Revolutionary New Smartphone", "A leading technology company launches its latest smartphone model, boasting innovative features and advanced capabilities. The device is expected to set new standards in the mobile industry.", R.drawable.c),
        TopNewsItem("COVID-19 Vaccination Drive Expands to Rural Communities", "Health authorities ramp up efforts to vaccinate rural populations against COVID-19, ensuring equitable access to vaccines. Mobile vaccination clinics and outreach programs are deployed to reach remote areas.", R.drawable.d),
        TopNewsItem("Space Agency Announces Plans for Moon Mission", "A national space agency reveals plans to send astronauts back to the Moon as part of ambitious lunar exploration efforts. The mission aims to establish a sustainable human presence on the lunar surface.", R.drawable.e),
        TopNewsItem("Artificial Intelligence Breakthrough Accelerates Drug Discovery", "Scientists make significant progress in drug discovery with the help of artificial intelligence algorithms.", R.drawable.f)
        // Add more top news items as needed
    )
    private val relatedNewsList = listOf(
        RelatedNewsItem("Scientists Discover New Species of Deep-Sea Fish", "Researchers have identified a previously unknown species of fish living in the depths of the Pacific Ocean. This discovery sheds light on the biodiversity of deep-sea ecosystems", R.drawable.a),
        RelatedNewsItem("Global Leaders Gather for Climate Summit in Paris", "World leaders convene in Paris to discuss urgent measures to combat climate change. The summit aims to strengthen international cooperation and accelerate efforts to reduce greenhouse gas emissions.", R.drawable.b),
        RelatedNewsItem("Tech Giant Unveils Revolutionary New Smartphone", "A leading technology company launches its latest smartphone model, boasting innovative features and advanced capabilities. The device is expected to set new standards in the mobile industry.", R.drawable.c),
        RelatedNewsItem("COVID-19 Vaccination Drive Expands to Rural Communities", "Health authorities ramp up efforts to vaccinate rural populations against COVID-19, ensuring equitable access to vaccines. Mobile vaccination clinics and outreach programs are deployed to reach remote areas.", R.drawable.d),
        RelatedNewsItem("Space Agency Announces Plans for Moon Mission", "A national space agency reveals plans to send astronauts back to the Moon as part of ambitious lunar exploration efforts. The mission aims to establish a sustainable human presence on the lunar surface.", R.drawable.e),
        RelatedNewsItem("Artificial Intelligence Breakthrough Accelerates Drug Discovery", "Scientists make significant progress in drug discovery with the help of artificial intelligence algorithms.", R.drawable.f)
        // Add more related news items as needed
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewMainPageBinding.inflate(inflater,container,false)
        val newMainPage:View = binding.root
        binding.apply {
            // Set up the Top News RecyclerView
            topNewsAdapter = TopNewsAdapter(requireContext(),topNewsList)
            binding.topnewsRecycler.adapter = topNewsAdapter
            binding.topnewsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            // Set up the Related News RecyclerView
            relatedNewsAdapter = RelatedNewsAdapter(requireContext(),relatedNewsList)
            binding.relatedNews.adapter = relatedNewsAdapter
            binding.relatedNews.layoutManager = GridLayoutManager(requireContext(),2)
        }
        return newMainPage
    }
}