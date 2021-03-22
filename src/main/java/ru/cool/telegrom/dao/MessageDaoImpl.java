package ru.cool.telegrom.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class MessageDaoImpl implements MessageDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);
    private final PostgresDataSource dataSource;

    private final String SEND_MESSAGE= "INSERT INTO MESSAGES (MESSAGE_TEXT, CHAT_ID) VALUES(?,?)";

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
            LOGGER.error("Ошибка записи сообщения", e);
        }
    }
}
