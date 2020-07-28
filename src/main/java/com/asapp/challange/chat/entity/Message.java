package com.asapp.challange.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "messageId")
    private int messageId;

    @Column(name = "type")
    private String type;

    @Column(name = "text")
    private String text;

    @Column(name = "timestamp")
    private String timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conversation conversation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "documentId", referencedColumnName = "documentId")
    private Document document;

}
