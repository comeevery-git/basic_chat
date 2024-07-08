package com.example.chat.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.application.usecase.ChatUseCase;
import com.example.chat.domain.entity.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ChatController {

    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        log.info("Message received: {}", chatMessage);
        chatUseCase.sendMessage(chatMessage);
        log.info("Message sent to recipient: {}", chatMessage.getRecipientId());
    }

    @GetMapping("/chat/{userId}")
    public ModelAndView getChatPage(@PathVariable String userId, ModelAndView m) {
        m.addObject("userId", userId);
        log.info("User {} entered chat", userId);
        return m;
    }

    @GetMapping("/api/chat/history")
    @ResponseBody
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam String senderId, @RequestParam String recipientId) {
        log.info("Getting chat history between {} and {}", senderId, recipientId);
        List<ChatMessage> history = chatUseCase.getChatHistory(senderId, recipientId);

        log.info("Chat history retrieved: {}", history);
        return ResponseEntity.ok(history);
    }

}