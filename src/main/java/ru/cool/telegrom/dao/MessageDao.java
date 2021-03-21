package ru.cool.telegrom.dao;

import ru.cool.telegrom.dao.model.Message;

/**
 * интерфейс для сообщений
 */

public interface MessageDao {

    void saveSendMessage(Message message,int chat_id);
}
