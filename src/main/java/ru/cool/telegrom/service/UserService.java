package ru.cool.telegrom.service;

import ru.cool.telegrom.model.LoginRequest;
import ru.cool.telegrom.model.RegistrationRequest;

/**
 * Интерфейс для работы с данными пользователей
 */
public interface UserService {

    /**
     * Регистрация нового пользователя
     */
    void registration(RegistrationRequest request);
    void loginOkay(LoginRequest loginRequest) throws Exception;
}
