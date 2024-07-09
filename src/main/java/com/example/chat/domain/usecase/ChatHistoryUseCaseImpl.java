package com.example.chat.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chat.application.usecase.ChatHistoryUseCase;
import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.domain.repository.ChatRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ChatHistoryUseCaseImpl implements ChatHistoryUseCase {
	private final ChatRepository chatRepository;

	public ChatHistoryUseCaseImpl(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	@Override
	public List<ChatMessage> getChatHistory(String senderId, String recipientId) {
		log.info("### Getting chat history between {} and {}", senderId, recipientId);
		return chatRepository.findMessagesBetweenUsers(senderId, recipientId);
	}

	@Override
	public List<String> getAllUsers() {
		return chatRepository.findAllUsers();
	}
}
