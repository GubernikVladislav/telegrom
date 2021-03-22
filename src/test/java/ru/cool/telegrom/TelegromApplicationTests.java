package ru.cool.telegrom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import ru.cool.telegrom.dao.model.Message;
import ru.cool.telegrom.model.RegistrationRequest;

@SpringBootTest
class TelegromApplicationTests {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void saveSendMessage() {

        Message message = new Message();
        message.setFrom("TEST2");
        message.setText("Zdravenki buli");
        message.setTo("Tesla");

        restTemplate.postForEntity("http://localhost:8080/messages/send", message, Void.class);

    }

}
