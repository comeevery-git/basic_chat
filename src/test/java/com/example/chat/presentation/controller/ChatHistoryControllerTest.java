package com.example.chat.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.chat.application.usecase.ChatHistoryUseCase;
import com.example.chat.domain.entity.ChatMessage;

@ExtendWith(MockitoExtension.class)
class ChatHistoryControllerTest {
    @Mock
    private ChatHistoryUseCase chatHistoryUseCase;

    private ChatHistoryController chatHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatHistoryController = new ChatHistoryController(chatHistoryUseCase);
    }

    @Test
    void getChatHistory_shouldReturnMessages() {
        String senderId = "sender1";
        String recipientId = "recipient1";
        ChatMessage message1 = new ChatMessage();
        message1.setSenderId(senderId);
        message1.setRecipientId(recipientId);
        message1.setContent("Hello");

        ChatMessage message2 = new ChatMessage();
        message2.setSenderId(recipientId);
        message2.setRecipientId(senderId);
        message2.setContent("Hi");

        List<ChatMessage> expectedMessages = List.of(
            message1, message2
        );
        when(chatHistoryUseCase.getChatHistory(senderId, recipientId)).thenReturn(expectedMessages);

        ResponseEntity<List<ChatMessage>> response = chatHistoryController.getChatHistory(senderId, recipientId);

        assertEquals(ResponseEntity.ok(expectedMessages), response);
        verify(chatHistoryUseCase).getChatHistory(senderId, recipientId);
    }
}