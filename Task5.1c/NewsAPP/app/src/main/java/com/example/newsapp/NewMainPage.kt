package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewMainPage : Fragment() {
    private lateinit var binding: FragmentNewsMainpage
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsMainpage.inflate(inflater, container, false)
        var favbinding : View = binding.root
        // Inflate the layout for this fragment
        binding = FragmentNewsMainpage.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_new_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can perform any additional setup for your fragment here
    }
}