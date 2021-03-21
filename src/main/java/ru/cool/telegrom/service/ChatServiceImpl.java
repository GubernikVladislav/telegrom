package ru.cool.telegrom.service;

import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.ChatDao;
import ru.cool.telegrom.dao.UserDao;
import ru.cool.telegrom.dao.model.Message;

@Service
public class ChatServiceImpl implements ChatService {

    private final UserDao userDao;
    private final ChatDao chatDao;

    public ChatServiceImpl(UserDao userDao, ChatDao chatDao) {
        this.userDao = userDao;
        this.chatDao = chatDao;
    }

    @Override
    public void sendMessage(Message message) {
        if (userDao.isUserLogined(message.getFrom())) {
            chatDao.createChatIfNotExists(message);
        } else {
            throw new RuntimeException("Попытка отправки сообщения незалогиненным пользователем");
        }
    }
}
