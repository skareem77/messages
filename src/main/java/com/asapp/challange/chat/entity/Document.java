package com.asapp.challange.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "documentId")
    private long documentId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "documentFormat")
    private String documentFormat;

    @Column(name = "url")
    private String url;

    @OneToOne(mappedBy = "document")
    private Message message;

}
