package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chatpage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText messageEditText;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;
    private ChatBotService chatBotService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);
        String username = getIntent().getStringExtra("USERNAME");
        recyclerView = findViewById(R.id.chatrecycler);
        messageEditText = findViewById(R.id.chatwithbot);
        sendButton = findViewById(R.id.sendbutton);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        chatBotService = ChatBotRetrofitClient.getClientWithLogging().create(ChatBotService.class);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToChatBot();
            }
        });
    }

    private void sendMessageToChatBot() {
        String messageText = messageEditText.getText().toString().trim();
        if (!messageText.isEmpty()) {
            addMessage(new Message(messageText, true));
            messageEditText.setText("");

            ChatBotRequest request = new ChatBotRequest(messageText, new ArrayList<>());
            Call<ChatBotResponse> call = chatBotService.getChatBotResponseWithLogging(request);
            call.enqueue(new Callback<ChatBotResponse>() {
                @Override
                public void onResponse(Call<ChatBotResponse> call, Response<ChatBotResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String message = response.body().getMessage();
                        addMessage(new Message(message, false));
                    } else {

                        Toast.makeText(Chatpage.this, "Failed to get response from chatbot", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ChatBotResponse> call, Throwable t) {
                    Toast.makeText(Chatpage.this, "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

    private void addMessage(Message message) {
        messageList.add(message);
        chatAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(messageList.size() - 1);
    }
}