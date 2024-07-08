package com.example.chat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@EnableJpaRepositories(basePackages = "com.example.chat.domain.repository")
@WebAppConfiguration
class ChatApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("ChatApplicationTests Context loaded.");
	}

}
