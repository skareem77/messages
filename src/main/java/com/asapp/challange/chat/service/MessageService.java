package com.asapp.challange.chat.service;


import com.asapp.challange.chat.entity.Message;
import com.asapp.challange.chat.model.MessageDetails;
import com.asapp.challange.chat.model.MessageDetailResponse;
import com.asapp.challange.chat.model.MessageListResponse;

import java.util.List;

public interface MessageService {
    Message saveMessage(MessageDetails messageDetails) throws Exception;
    MessageListResponse getMessage(int recipient, int start, int limit);
}
