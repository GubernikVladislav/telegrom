package ru.cool.telegrom.model;

public class ChatRequest {

    private String type;

    private Integer admin;

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
