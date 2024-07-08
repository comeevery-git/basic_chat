package com.example.chat.presentation.controller;

import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.application.usecase.ChatUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private ChatUseCase chatUseCase;

    private ChatController chatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatController = new ChatController(chatUseCase);
    }

    @Test
    void sendMessage_shouldSaveAndSendMessage() {
        String senderId = "sender1";
        String recipientId = "recipient1";
        ChatMessage message = new ChatMessage();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);
        message.setContent("Hello");

        when(chatUseCase.sendMessage(message)).thenReturn(message);

        chatController.sendMessage(message);

        verify(chatUseCase).sendMessage(message);
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
        when(chatUseCase.getChatHistory(senderId, recipientId)).thenReturn(expectedMessages);

        ResponseEntity<List<ChatMessage>> response = chatController.getChatHistory(senderId, recipientId);

        assertEquals(ResponseEntity.ok(expectedMessages), response);
        verify(chatUseCase).getChatHistory(senderId, recipientId);
    }
}