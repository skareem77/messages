package com.asapp.challange.chat.service.impl;

import com.asapp.challange.chat.entity.Conversation;
import com.asapp.challange.chat.entity.Document;
import com.asapp.challange.chat.entity.Message;
import com.asapp.challange.chat.model.Content;
import com.asapp.challange.chat.model.MessageDetails;
import com.asapp.challange.chat.model.MessageDetailResponse;
import com.asapp.challange.chat.model.MessageListResponse;
import com.asapp.challange.chat.model.MetaData;
import com.asapp.challange.chat.repository.ConversationRepository;
import com.asapp.challange.chat.repository.CustomMessageRepository;
import com.asapp.challange.chat.repository.MessageRepository;
import com.asapp.challange.chat.repository.UserRepository;
import com.asapp.challange.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomMessageRepository customMessageRepository;


    @Override
    public Message saveMessage(MessageDetails messageDetails) throws Exception {
        Conversation conversation = conversation(messageDetails.getSender(), messageDetails.getRecipient());
        if (messageDetails.getContent().getType().equals("image") || messageDetails.getContent().getType().equals("video")) {
           return saveMultiMediaMessage(conversation, messageDetails);
        }
        return insertMessage(conversation, messageDetails);
    }

    @Override
    @Transactional
    public MessageListResponse getMessage(int recipient, int start, int limit) {
        List<Message> messages = customMessageRepository.findMessagesLimited(recipient, start, limit);
        MessageListResponse messageListResponse = new MessageListResponse();
        List<MessageDetailResponse> listResponses = new ArrayList<>();
        messages.forEach(msg -> {
            MessageDetailResponse msgResponse = new MessageDetailResponse();
            msgResponse.setId(msg.getMessageId());
            msgResponse.setSender(msg.getConversation().getSender());
            msgResponse.setRecipient(msg.getConversation().getRecipient());
            msgResponse.setTimestamp(msg.getTimestamp());
            msgResponse.setContent(new Content(msg.getType(), msg.getText()));
            if (msg.getDocument() != null) {
                MetaData metaData = new MetaData();
                Document document = msg.getDocument();
                metaData.setFormat(document.getDocumentFormat());
                metaData.setName(document.getFileName());
                metaData.setSize(20);
                metaData.setUrl(document.getUrl());
                msgResponse.setMetaData(metaData);
            }
            listResponses.add(msgResponse);
        });
        messageListResponse.setMessageList(listResponses);
        return messageListResponse;
    }

    @Transactional
    private Message saveMultiMediaMessage(Conversation conversation, MessageDetails messageDetails) throws Exception {
        // multi media service
        Document document =  new Document();
        document.setUrl("document_url");
        document.setDocumentFormat("jpeg");
        document.setFileName("test_filename");

        Message msg = new Message();
        msg.setConversation(conversation);
        msg.setType(messageDetails.getContent().getType());
        msg.setText(messageDetails.getContent().getText());
        msg.setTimestamp(getTodayDate());
        msg.setDocument(document);
        return messageRepository.save(msg);
    }

    @Transactional
    private Message insertMessage(Conversation conversation, MessageDetails messageDetails) {
        Message msg = new Message();
        msg.setConversation(conversation);
        msg.setType(messageDetails.getContent().getType());
        msg.setText(messageDetails.getContent().getText());
        msg.setTimestamp(getTodayDate());
        return messageRepository.save(msg);
    }

    @Transactional
    private Conversation conversation(int senderId, int recipientId) throws Exception {
        List<Conversation> conversations = conversationRepository.findConversationId(senderId, recipientId);
        if (CollectionUtils.isEmpty(conversations)) {
            //Validate sender and recipient
            userRepository.findById(recipientId).orElseThrow(()->new Exception("Invalid Recipient Id"));
            userRepository.findById(senderId).orElseThrow(() -> new Exception("Invalid Sender Id"));
            // create new conversation
            Conversation cs = new Conversation();
            cs.setRecipient(recipientId);
            cs.setSender(senderId);
            return conversationRepository.save(cs);
        }
        return conversations.get(0);
    }

    private String getTodayDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }
}
