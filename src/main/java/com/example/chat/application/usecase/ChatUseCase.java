package com.example.chat.application.usecase;

import java.util.List;

import com.example.chat.domain.entity.ChatMessage;

public interface ChatUseCase {
    ChatMessage sendMessage(ChatMessage message);
    List<ChatMessage> getChatHistory(String senderId, String recipientId);
}