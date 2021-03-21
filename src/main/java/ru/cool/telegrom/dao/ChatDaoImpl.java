package ru.cool.telegrom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.model.Message;

import java.sql.*;

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
    public int createChatIfNotExists(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_CHAT_EXISTS)) {
            statement.setString(1, message.getFrom());
            statement.setString(2, message.getTo());
            statement.setString(3, message.getFrom());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
               int chat_id = createChat(message);

                return chat_id;
            }

        } catch (Exception e) {

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

        }
        return 0;
    }

}
