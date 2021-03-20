package ru.cool.telegrom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.model.ChatRequest;


@Service
public class ChatServiceImpl implements ChatService{


    private final ChatService chatService;
    @Autowired
    public ChatServiceImpl(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void createChat(ChatRequest chatRequest) {


    }
}
