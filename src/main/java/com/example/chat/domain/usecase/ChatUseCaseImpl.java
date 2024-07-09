package com.example.chat.domain.usecase;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chat.application.usecase.ChatUseCase;
import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.domain.repository.ChatRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ChatUseCaseImpl implements ChatUseCase {

	private final SimpMessagingTemplate messagingTemplate;
	private final ChatRepository chatRepository;

	public ChatUseCaseImpl(SimpMessagingTemplate messagingTemplate, ChatRepository chatRepository) {
		this.messagingTemplate = messagingTemplate;
		this.chatRepository = chatRepository;
	}

	@Override
	public ChatMessage sendMessage(ChatMessage message) {
		log.info("### Message received: {}", message);

		ChatMessage savedMessage = chatRepository.save(message);
		log.info("### Message saved: {}", savedMessage);

		messagingTemplate.convertAndSendToUser(
			savedMessage.getRecipientId(),
			"/queue/messages",
			savedMessage
		);
		log.info("Message sent to recipient: {}", savedMessage.getRecipientId());

		return savedMessage;
	}
}
