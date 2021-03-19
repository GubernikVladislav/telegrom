package ru.cool.telegrom.model;

public class LoginRequest {

    private String login;

    private String password;

    private boolean loginOkay = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isLoginOkay() {
        return loginOkay;
    }

    public void setLoginOkay(boolean loginOkay) {
        this.loginOkay = loginOkay;
    }

}
