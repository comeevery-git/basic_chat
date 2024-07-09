package com.example.chat.application.usecase;

import com.example.chat.domain.entity.ChatMessage;

public interface ChatUseCase {
    ChatMessage sendMessage(ChatMessage message);
}