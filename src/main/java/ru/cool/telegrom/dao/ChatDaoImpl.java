package ru.cool.telegrom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.model.Message;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class ChatDaoImpl implements ChatDao {

    private final PostgresDataSource dataSource;

    private final String CHECK_CHAT_EXISTS = "SELECT * FROM CHAT C" +
            "JOIN USER_CHAT UC ON UC.CHAT_ID = C.ID" +
            "JOIN USERINFO U ON U.ID = UC.USER_ID" +
            "WHERE U.LOGIN IN (?,?) AND C.ADMIN = (SELECT ID FROM USERINFO WHERE LOGIN = ?)";

    @Autowired
    public ChatDaoImpl(PostgresDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createChatIfNotExists(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_CHAT_EXISTS)) {
            statement.setString(1, message.getFrom());
            statement.setString(2, message.getTo());
            statement.setString(3, message.getFrom());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                createChat(message);
            }

        } catch (Exception e) {

        }
    }

    private void createChat(Message message) {

    }
}
