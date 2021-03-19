package ru.cool.telegrom.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.telegrom.dao.UserDao;
import ru.cool.telegrom.dao.model.User;
import ru.cool.telegrom.model.LoginRequest;
import ru.cool.telegrom.model.RegistrationRequest;



@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void registration(RegistrationRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        userDao.saveNewUser(user);
    }

    @Override
    public void loginOkay(LoginRequest loginRequest) throws Exception{

            if (userDao.checkLogin(loginRequest)) {
                userDao.setLoginOkay(loginRequest);
            }else {
                throw new Exception("Неверный логин или пароль" );
            }

    }


}
