package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



    public class Videoplaypage extends AppCompatActivity {
        private static final String YOUTUBE_API_KEY ="AIzaSyCtb9zutFoDujDfbH8rOqvVzeByiQ9S8T4";
        private WebView mWebView;
        private String mYoutubeUrl;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_videoplaypage);

            mYoutubeUrl = getIntent().getStringExtra("youtubeUrl");

            mWebView = findViewById(R.id.webview);
            // Configure WebView settings
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true); // Enable JavaScript
            webSettings.setDomStorageEnabled(true); // Enable local storage

            // Set a WebViewClient to handle navigation within the WebView
            mWebView.setWebViewClient(new WebViewClient());

            // Load the YouTube URL in the WebView
            mWebView.loadUrl(mYoutubeUrl);
        }
    }