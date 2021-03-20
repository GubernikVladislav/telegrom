package ru.cool.telegrom.dao;

import ru.cool.telegrom.model.ChatRequest;

/**
 * Интерфейс для работы с БД
 */
public interface MessageDao {

    /**
     * СОздание нового чата
     * для общения между залогинеными пользоветелями
     */
    void createChat(ChatRequest chatRequest);
    boolean loginOkay();
}