package ru.cool.telegrom.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.telegrom.model.ChatRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Component
public class MessageDaoImpl implements MessageDao {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);

    private final PostgresDataSource dataSource;

    @Autowired
    public MessageDaoImpl(PostgresDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createChat(ChatRequest chatRequest) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO CHAT (ADMIN,TYPE) VALUES (?,?)")) {

            statement.setInt(1, chatRequest.getAdmin());
            statement.setString(2, chatRequest.getType());

            statement.execute();
        } catch (SQLException throwables) {
            LOGGER.error("Ошибка создания чата");
        }

    }

    @Override
    public boolean loginOkay() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "Select * from userinfo ui\n" +
                             "join user_chat uc on uc.user_id = ui.id\n" +
                             "join chat c on c.id = uc.chat_id\n" +
                             "WHERE LOGINOKAY = TRUE ")) {

        } catch (SQLException throwables) {
            LOGGER.error("");
        }

        return true;
    }


}
