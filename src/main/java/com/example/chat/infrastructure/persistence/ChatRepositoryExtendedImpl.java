package com.example.chat.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.chat.domain.entity.ChatMessage;
import com.example.chat.domain.entity.QChatMessage;
import com.example.chat.domain.repository.ChatRepositoryExtended;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ChatRepositoryExtendedImpl implements ChatRepositoryExtended {

	private final JPAQueryFactory queryFactory;

	@PersistenceContext
	private EntityManager entityManager;

	public ChatRepositoryExtendedImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<ChatMessage> findMessagesBetweenUsers(String senderId, String recipientId) {
		log.info("Getting chat history between {} and {}", senderId, recipientId);

		QChatMessage chatMessage = QChatMessage.chatMessage;

		return queryFactory
			.selectFrom(chatMessage)
			.where(chatMessage.senderId.eq(senderId).and(chatMessage.recipientId.eq(recipientId))
				.or(chatMessage.senderId.eq(recipientId).and(chatMessage.recipientId.eq(senderId))))
			.orderBy(chatMessage.timestamp.asc())
			.fetch();
	}

}