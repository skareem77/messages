package com.asapp.challange.chat.model;

public class Content {

    private String type;
    private String text;

    public Content(String type, String text) {
        this.text = text;
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
