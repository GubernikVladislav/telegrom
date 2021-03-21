package ru.cool.telegrom.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class MessageDaoImpl implements MessageDao{


    private final PostgresDataSource dataSource;

    private final String SEND_MESSAGE= "INSERT INTO MESSAGES (MESSAGE_TEXT) VALUES(?,(" +
            "SELECT ID FROM CHAT U JOIN MESSAGES UI " +
            "ON U.ID = UI.CHAT_ID WHERE ID = ?))";


    @Autowired
    public MessageDaoImpl(PostgresDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveSendMessage(Message message, int chat_id) {
        try (Connection conection = dataSource.getConnection();
             PreparedStatement statement = conection.prepareStatement(SEND_MESSAGE)) {

            statement.setString(1, message.getText());
            statement.setInt(2,chat_id);

            statement.execute();
        } catch (Exception e){


        }




    }
}
