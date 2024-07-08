package com.example.chat.infrastructure.persistence;

import com.example.chat.domain.entity.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatMessageCache {
    private ConcurrentHashMap<String, List<ChatMessage>> chatHistory = new ConcurrentHashMap<>();

    public void cacheMessage(ChatMessage message) {
        String chatId = getChatId(message.getSenderId(), message.getRecipientId());
        chatHistory.computeIfAbsent(chatId, k -> new ArrayList<>()).add(message);
    }

    public List<ChatMessage> getCachedChatHistory(String senderId, String recipientId) {
        String chatId = getChatId(senderId, recipientId);
        return chatHistory.getOrDefault(chatId, new ArrayList<>());
    }

    private String getChatId(String senderId, String recipientId) {
        return senderId.compareTo(recipientId) < 0
            ? senderId + "_" + recipientId
            : recipientId + "_" + senderId;
    }

    public void clearCache() {
        chatHistory.clear();
    }
}