package ru.cool.telegrom.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.model.Message;

import java.sql.*;

@Service
public class ChatDaoImpl implements ChatDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDaoImpl.class);

    private final PostgresDataSource dataSource;

    private final String CHECK_CHAT_EXISTS = "SELECT * FROM CHAT C" +
            " JOIN USER_CHAT UC ON UC.CHAT_ID = C.ID" +
            " JOIN USERINFO U ON U.ID = UC.USER_ID" +
            " WHERE U.LOGIN IN (?,?) AND C.ADMIN = (SELECT ID FROM USERINFO WHERE LOGIN = ?)";

    @Autowired
    public ChatDaoImpl(PostgresDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int createChatIfNotExists(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_CHAT_EXISTS)) {
            statement.setString(1, message.getFrom());
            statement.setString(2, message.getTo());
            statement.setString(3, message.getFrom());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                int chat_id = createChat(message);

                setChatUser(message, chat_id);

                return chat_id;
            }

        } catch (Exception e) {
            LOGGER.error("Ошибка добавления нового чата", e);
        }
        return 0;
    }

    private final String NEW_MASSAGE = "Insert into chat (type,admin) values (?, (select ui.id from userinfo ui where ui.login = ?))";

    private int createChat(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEW_MASSAGE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, "Private");
            statement.setString(2, message.getFrom());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int сhat_id = rs.getInt(1);
                return сhat_id;
            }
            statement.execute();
        } catch (Exception e) {
            LOGGER.error("Ошибка создания чата", e);
        }
        return 0;
    }

    private final String SET_USER_CHAT = "INSERT INTO USER_CHAT (CHAT_ID,USER_ID) " +
            " VALUES (?,(SELECT ID FROM USERINFO WHERE LOGIN = ?))";

    private void setChatUser(Message message, int chat) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_USER_CHAT)) {

            statement.setInt(1, chat);
            statement.setString(2, message.getFrom());

            statement.execute();
        } catch (SQLException throwables) {
            LOGGER.error("неудачное создание чата");
        }
    }


}
