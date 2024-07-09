package com.example.chat.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.domain.repository.ChatRepository;
import com.example.chat.domain.usecase.ChatHistoryUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class ChatUseCaseHistoryImplTest {
    @Mock
    private ChatRepository chatRepository;

    private ChatHistoryUseCase chatHistoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatHistoryUseCase = new ChatHistoryUseCaseImpl(chatRepository);
    }

    @Test
    void getChatHistory_shouldReturnMessages() {
        // Given
        String senderId = "sender1";
        String recipientId = "recipient1";
        ChatMessage message1 = new ChatMessage();
        message1.setRecipientId(recipientId);
        message1.setSenderId(senderId);
        message1.setContent("Hello");
        ChatMessage message2 = new ChatMessage();
        message2.setRecipientId(senderId);
        message2.setSenderId(recipientId);
        message2.setContent("Hi there");
        List<ChatMessage> expectedMessages = List.of(
            message1, message2
        );

        when(chatRepository.findMessagesBetweenUsers(senderId, recipientId)).thenReturn(expectedMessages);

        List<ChatMessage> result = chatHistoryUseCase.getChatHistory(senderId, recipientId);

        assertEquals(expectedMessages, result);
        verify(chatRepository).findMessagesBetweenUsers(senderId, recipientId);
    }
}
