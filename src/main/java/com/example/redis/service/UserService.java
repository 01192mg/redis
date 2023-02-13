package com.example.redis.service;

import com.example.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserService {
    public static final String NAME_KEY = "nameKey:";
    public static final int TIME_TO_LIVE = 10;
    private final ExternalApiService externalApiService;
    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {
        var operations = redisTemplate.opsForValue();

        String userName;
        int userAge;

        String cachedName = operations.get(NAME_KEY + userId);

        if (cachedName != null) {
            userName = cachedName;
        } else {
            userName = externalApiService.getUserName(userId);
            operations.set(NAME_KEY + userId, userName, TIME_TO_LIVE, TimeUnit.SECONDS);
        }

        userAge = externalApiService.getUserAge(userId);
        return new UserProfile(userName, userAge);
    }
}
