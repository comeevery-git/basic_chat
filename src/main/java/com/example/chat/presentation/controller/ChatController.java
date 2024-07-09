package com.example.chat.presentation.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.chat.application.usecase.ChatUseCase;
import com.example.chat.domain.entity.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        log.info("### Message received: {}", chatMessage);
        chatUseCase.sendMessage(chatMessage);
        log.info("### Message sent to recipient: {}", chatMessage.getRecipientId());
    }

    @GetMapping("/chat")
    public String getChatPage(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        log.info("### Chat page requested for user {}", userId);
        return "chat";
    }

}