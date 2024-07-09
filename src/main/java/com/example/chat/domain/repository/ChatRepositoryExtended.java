package com.example.chat.domain.repository;

import com.example.chat.domain.entity.ChatMessage;
import java.util.List;

public interface ChatRepositoryExtended {
	List<ChatMessage> findMessagesBetweenUsers(String senderId, String recipientId);
	List<String> findAllUsers();

}

