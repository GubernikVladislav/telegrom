package ru.cool.telegrom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cool.telegrom.dao.model.Message;
import ru.cool.telegrom.model.ChatRequest;
import ru.cool.telegrom.service.ChatService;

@RestController
@RequestMapping(path = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final ChatService chatService;

    @Autowired
    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) {
        chatService.sendMessage(message);
    }
}
