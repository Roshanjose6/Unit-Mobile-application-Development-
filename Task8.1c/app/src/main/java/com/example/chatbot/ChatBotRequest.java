package com.example.chatbot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatBotRequest {

    @SerializedName("userMessage")
    private String userMessage;

    @SerializedName("chatHistory")
    private List<ChatHistoryItem> chatHistory;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public List<ChatHistoryItem> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ChatHistoryItem> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ChatBotRequest(String userMessage, List<ChatHistoryItem> chatHistory) {
        this.userMessage = userMessage;
        this.chatHistory = chatHistory;
    }

}