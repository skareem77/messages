package com.asapp.challange.chat.controller;

import com.asapp.challange.chat.entity.Message;
import com.asapp.challange.chat.model.Error;
import com.asapp.challange.chat.model.MessageDetails;
import com.asapp.challange.chat.model.MessageListResponse;
import com.asapp.challange.chat.model.MessageResponse;
import com.asapp.challange.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity sendMessage(@RequestBody MessageDetails messageDetails) {
        ResponseEntity responseEntity;
        try {
            Message msg = messageService.saveMessage(messageDetails);
            MessageResponse messageResponse = new MessageResponse(msg.getMessageId(), msg.getTimestamp());
            responseEntity = ResponseEntity.ok().body(messageResponse);
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
        return responseEntity;
    }

    @GetMapping("/messages")
    public ResponseEntity recipientMessages(@RequestParam("recipient") int recipient,
                                            @RequestParam("start") int start,
                                            @RequestParam(value = "limit", defaultValue = "100")  int limit) {
        ResponseEntity responseEntity;
        try {
            MessageListResponse responseList = messageService.getMessage(recipient, start, limit);
            responseEntity = ResponseEntity.ok().body(responseList);
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
        return responseEntity;
    }
}
