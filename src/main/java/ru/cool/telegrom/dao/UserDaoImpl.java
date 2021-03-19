package ru.cool.telegrom.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.telegrom.dao.model.User;
import ru.cool.telegrom.model.LoginRequest;

import java.sql.*;

@Component
public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private final PostgresDataSource dataSource;

    private final String NEW_USER_STATEMENT = "INSERT INTO userinfo (LOGIN, PASSWORD, EMAIL) VALUES (?,?,?)";

    @Autowired
    public UserDaoImpl(PostgresDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveNewUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEW_USER_STATEMENT)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());

            statement.execute();
        } catch (SQLException throwables) {
            LOGGER.error("Ошибка записи нового пользователя");
        }
    }


    @Override
    public boolean checkLogin(LoginRequest loginRequest) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERINFO WHERE LOGIN=?")) {
            statement.setString(1, loginRequest.getLogin());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {

                String password = resultSet.getString("password");
                if (password.equals(loginRequest.getPassword())) {
                    return true;
                }else {
                    return false;
                }
            }

        } catch (SQLException throwables) {
            LOGGER.error("неверный пароль");
        }

        return true;
    }

    public void setLoginOkay(LoginRequest loginRequest){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE USERINFO SET LOGINOKAY = true WHERE PASSWORD=? AND LOGIN=?")) {

            statement.setString(1, loginRequest.getPassword());
            statement.setString(2, loginRequest.getLogin());

            statement.execute();
        } catch (SQLException throwables) {
            LOGGER.error("Ошибка изменения состояния");
        }
    }



}