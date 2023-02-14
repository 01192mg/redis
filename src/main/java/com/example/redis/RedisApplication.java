package com.example.redis;

import com.example.redis.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@RequiredArgsConstructor
@EnableCaching
@SpringBootApplication
public class RedisApplication implements CommandLineRunner {
	private final ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Application started..");
		chatService.enterChatRoom("chat1");
	}
}
