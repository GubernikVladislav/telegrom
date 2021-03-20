package ru.cool.telegrom.dao;

import ru.cool.telegrom.dao.model.User;
import ru.cool.telegrom.model.LoginRequest;


/**
 * Интерфейс для работы с БД
 */
public interface UserDao {

    /**
     * Добавить нового пользователя
     * @param user данные пользователя
     */
    void saveNewUser(User user);
    /**
     * Проверка данных пользователя
     */
    boolean checkLogin(LoginRequest loginRequest);
    void setLoginOkay(LoginRequest loginRequest);

}
