package com.example.chatbot;

public class Message {
    private String message;
    private boolean userMessage;
    public Message(String message, boolean userMessage) {
        this.message = message;
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUserMessage() {
        return userMessage;
    }
}