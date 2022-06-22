package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

@Controller
public class ChatController {
    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String rootMapping() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String x(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        model.addAttribute("userDTO", userDTO);
        return "home_twitter/chat";
    }

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//    @Autowired
//    private ChatMessageService chatMessageService;
//    @Autowired
//    private ChatRoomService chatRoomService;
//
//
//    @PermitAll
//    @GetMapping("/example/{followId}")
//    public String x(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model, @PathVariable Long followId) {
//        UserDTO userDTO;
//        if (userDetails == null) {
//            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
//        } else {
//            userDTO = userService.findByEmail(userDetails.getEmail()).get();
//        }
//        UserDTO followerDTO = userService.findById(followId).get();
//        model.addAttribute("userDTO", userDTO);
//        model.addAttribute("followerDTO", followerDTO);
//        return "home_twitter/chat";
//    }
//
//    @MessageMapping("/chat")
//    public void processMessage(@Payload ChatMessageEntity chatMessage) {
//        String chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true).get();
//        chatMessage.setChatId(chatId);
//
//        ChatMessageEntity saved = chatMessageService.save(chatMessage);
//
//        messagingTemplate.convertAndSendToUser(
//                chatMessage.getRecipientId(),"/queue/messages",
//                new ChatNotification(
//                        saved.getId(),
//                        saved.getSenderId(),
//                        saved.getSenderName()));
//    }
//
//    @GetMapping("/messages/{senderId}/{recipientId}/count")
//    public ResponseEntity<Long> countNewMessages(
//            @PathVariable String senderId,
//            @PathVariable String recipientId) {
//
//        return ResponseEntity
//                .ok(chatMessageService.countNewMessages(senderId, recipientId));
//    }
//
//    @GetMapping("/messages/{senderId}/{recipientId}")
//    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
//                                                @PathVariable String recipientId) {
//        return ResponseEntity
//                .ok(chatMessageService.findChatMessages(senderId, recipientId));
//    }
//
//    @GetMapping("/messages/{id}")
//    public ResponseEntity<?> findMessage ( @PathVariable String id) {
//        return ResponseEntity
//                .ok(chatMessageService.findById(id));
//    }
}
