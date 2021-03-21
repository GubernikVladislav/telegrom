package ru.cool.telegrom.dao.model;

/**
 * Модель сообщения
 */
public class Message {

    /**
     * От кого
     */
    private String from;

    /**
     * Кому
     */
    private String to;

    /**
     * Текст
     */
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
