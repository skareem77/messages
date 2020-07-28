package com.asapp.challange.chat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MessageListResponse {

    @JsonProperty("messages")
    private List<MessageDetailResponse> messageList;

    public List<MessageDetailResponse> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageDetailResponse> messageList) {
        this.messageList = messageList;
    }
}
