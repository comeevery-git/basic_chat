package com.example.chat.presentation.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chat.application.usecase.ChatUseCase;
import com.example.chat.domain.entity.ChatMessage;

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
}