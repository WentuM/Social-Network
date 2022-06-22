package ru.kpfu.itis.demo.blog.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.demo.blog.web.entity.ChatRoomEntity;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
    Optional<ChatRoomEntity> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
