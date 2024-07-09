package com.example.chat.application.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.domain.repository.ChatRepository;
import com.example.chat.domain.usecase.ChatUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class ChatUseCaseImplTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private ChatRepository chatRepository;

    private ChatUseCase chatUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatUseCase = new ChatUseCaseImpl(messagingTemplate, chatRepository);
    }

    @Test
    void sendMessage_shouldSaveAndSendMessage() {
        ChatMessage message = new ChatMessage();
        message.setRecipientId("recipient1");
        message.setSenderId("sender1");
        message.setContent("Hello");

        when(chatRepository.save(message)).thenReturn(message);

        chatUseCase.sendMessage(message);

        verify(chatRepository).save(message);
        verify(messagingTemplate).convertAndSendToUser(
            eq("recipient1"),
            eq("/queue/messages"),
            eq(message)
        );
    }
}
