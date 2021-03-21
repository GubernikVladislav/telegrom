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
    boolean checkPassword(LoginRequest loginRequest);

    /**
     * Проверка, что пользователь залогинен
     *
     * @param login логин пользователя
     * @return true, если пользователь залогинен
     */
    boolean isUserLogined(String login);

    void setLoginOkay(LoginRequest loginRequest);

}
