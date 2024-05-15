package com.example.chatbot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatBotService {
    @POST("chat")
    Call<ChatBotResponse> getChatBotResponseWithLogging(@Body ChatBotRequest request);
}
