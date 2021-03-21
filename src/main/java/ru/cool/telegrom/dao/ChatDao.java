package ru.cool.telegrom.dao;

import ru.cool.telegrom.dao.model.Message;

/**
 * ДАО-слой для чатов
 */
public interface ChatDao {

    /**
     * Создать чат, если еще не создан для переданной пары пользователей
     *
     * @param message данные нового сообщения
     */
    int createChatIfNotExists(Message message);
}
