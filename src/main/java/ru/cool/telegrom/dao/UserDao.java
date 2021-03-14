package ru.cool.telegrom.dao;

import ru.cool.telegrom.dao.model.User;

/**
 * Интерфейс для работы с БД
 */
public interface UserDao {

    /**
     * Добавить нового пользователя
     * @param user данные пользователя
     */
    void saveNewUser(User user);
}
