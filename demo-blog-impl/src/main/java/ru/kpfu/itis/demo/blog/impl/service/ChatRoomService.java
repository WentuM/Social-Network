package ru.kpfu.itis.demo.blog.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.impl.entity.ChatRoomEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {

        return Optional.of(chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoomEntity::getChatId)
                .orElseGet(() -> {
                    if (!createIfNotExist) {
                        return "";
                    }
                    String chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoomEntity senderRecipient = ChatRoomEntity
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoomEntity recipientSender = ChatRoomEntity
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId).get();
                }));
    }
}
