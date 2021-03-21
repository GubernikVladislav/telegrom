package ru.cool.telegrom.service;

import ru.cool.telegrom.dao.model.Message;

public interface ChatService {

    /**
     * Отправить сообщение
     *
     * @param message данные сообщения
     */
    void sendMessage(Message message);
}
