package com.asapp.challange.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
@Table(name = "conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Conversation.findConversationId",
            query = "SELECT c FROM Conversation c WHERE c.sender = :senderId AND c.recipient = :recipientId")
public class Conversation {

    @Id
    @GeneratedValue
    @Column(name = "conversationId")
    private int conversationId;

    @Column(name = "sender")
    private int sender;

    @Column(name = "recipient")
    private int recipient;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conversationId")
    private List<Message> messageList;

}
