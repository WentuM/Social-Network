package ru.kpfu.itis.demo.blog.impl.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.demo.blog.impl.entity.ChatMessageEntity;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, String> {

    long countBySenderIdAndRecipientId(
            String senderId, String recipientId);

    List<ChatMessageEntity> findByChatId(String chatId);
}
