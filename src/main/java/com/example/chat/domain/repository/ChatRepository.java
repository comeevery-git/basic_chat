package com.example.chat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.chat.domain.entity.ChatMessage;

public interface ChatRepository extends JpaRepository<ChatMessage, String>, QuerydslPredicateExecutor<ChatMessage>,
	ChatRepositoryExtended {
    // 예시 List<ChatMessage> findAllByUserId(String userId);
}