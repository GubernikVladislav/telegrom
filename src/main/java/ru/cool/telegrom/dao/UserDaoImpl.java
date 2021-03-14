package ru.cool.telegrom.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.telegrom.dao.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
