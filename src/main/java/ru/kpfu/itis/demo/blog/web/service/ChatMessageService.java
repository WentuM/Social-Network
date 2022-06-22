package ru.kpfu.itis.demo.blog.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.web.entity.ChatMessageEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.ChatMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;

    public ChatMessageEntity save(ChatMessageEntity chatMessage) {
//        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientId(
                senderId, recipientId);
    }

    public List<ChatMessageEntity> findChatMessages(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatId(senderId, recipientId, false).get();

        ArrayList<ChatMessageEntity> messages = (ArrayList<ChatMessageEntity>) repository.findByChatId(chatId);

//        if(messages.size() > 0) {
//            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
//        }

        return messages;
    }

    public ChatMessageEntity findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
//                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(id,"can't find message (" + id + ")"));
    }

//    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
//        Query query = new Query(
//                Criteria
//                        .where("senderId").is(senderId)
//                        .and("recipientId").is(recipientId));
//        Update update = Update.update("status", status);
//        mongoOperations.updateMulti(query, update, ChatMessage.class);
//    }
}
