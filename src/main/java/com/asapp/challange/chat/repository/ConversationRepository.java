package com.asapp.challange.chat.repository;

import com.asapp.challange.chat.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    List<Conversation> findConversationId(int senderId, int recipientId);
}
