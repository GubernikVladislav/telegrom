package ru.cool.telegrom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import ru.cool.telegrom.model.RegistrationRequest;

@SpringBootTest
class TelegromApplicationTests {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void saveNewUserTest() {

        RegistrationRequest request = new RegistrationRequest();
        request.setLogin("Tesla");
        request.setPassword("Tes652");
        request.setEmail("Test_2@t67_2.ru");

        restTemplate.postForEntity("http://localhost:8080/user/login", request, Void.class);

    }

}
