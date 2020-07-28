package com.asapp.challange.chat.repository;


import com.asapp.challange.chat.entity.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomMessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> findMessagesLimited(int recipient, int start, int limit) {
        return entityManager.createQuery("SELECT m FROM Message m JOIN m.conversation c WHERE " +
                " c.recipient = ?1  AND m.messageId >= ?2 Order by m.messageId", Message.class)
                .setParameter(1, recipient).setParameter(2, start)
                .setMaxResults(limit).getResultList();
    }
}
