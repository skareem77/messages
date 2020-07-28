package com.asapp.challange.chat.model;

public class MessageResponse {
    private int id;
    private String timestamp;

    public MessageResponse() {}
    public MessageResponse(int id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
