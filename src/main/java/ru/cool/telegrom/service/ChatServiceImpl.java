package ru.cool.telegrom.service;

import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.ChatDao;
import ru.cool.telegrom.dao.MessageDao;
import ru.cool.telegrom.dao.UserDao;
import ru.cool.telegrom.dao.model.Message;

@Service
public class ChatServiceImpl implements ChatService {

    private final MessageDao messageDao;
    private final UserDao userDao;
    private final ChatDao chatDao;

    public ChatServiceImpl(MessageDao messageDao, UserDao userDao, ChatDao chatDao) {
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.chatDao = chatDao;
    }

    @Override
    public void sendMessage(Message message) {
        if (userDao.isUserLogined(message.getFrom())) {
            int chat_id = chatDao.createChatIfNotExists(message);
            messageDao.saveSendMessage(message,chat_id);
        } else {
            throw new RuntimeException("Попытка отправки сообщения незалогиненным пользователем");
        }
    }
}
