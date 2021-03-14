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
        request.setLogin("Test_2");
        request.setPassword("Test_2");
        request.setEmail("Test_2@test_2.ru");

        restTemplate.postForEntity("http://localhost:8080/registration", request, Void.class);
    }

}
