package com.example.chatbot;

import com.google.gson.annotations.SerializedName;

public class ChatHistoryItem {

    @SerializedName("User")
    private String user;

    @SerializedName("Llama")
    private String llama;

    // Constructor, getters, and setters
    public ChatHistoryItem(String user, String llama) {
        this.user = user;
        this.llama = llama;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLlama() {
        return llama;
    }

    public void setLlama(String llama) {
        this.llama = llama;
    }
}