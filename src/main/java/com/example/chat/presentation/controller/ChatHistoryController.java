package com.example.chat.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chat.application.usecase.ChatHistoryUseCase;
import com.example.chat.domain.entity.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/chat")
public class ChatHistoryController {
	private final ChatHistoryUseCase chatHistoryUseCase;

	public ChatHistoryController(ChatHistoryUseCase chatHistoryUseCase) {
		this.chatHistoryUseCase = chatHistoryUseCase;
	}

	@GetMapping("/history")
	@ResponseBody
	public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam String senderId, @RequestParam String recipientId) {
		log.info("### Getting chat history between {} and {}", senderId, recipientId);
		List<ChatMessage> history = chatHistoryUseCase.getChatHistory(senderId, recipientId);

		log.info("### Chat history retrieved: {}", history);
		return ResponseEntity.ok(history);
	}

	@GetMapping("/users")
	public ResponseEntity<List<String>> getAllUsers() {
		List<String> users = chatHistoryUseCase.getAllUsers();
		return ResponseEntity.ok(users);
	}

}
