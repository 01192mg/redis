package com.example.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RankingService {
    public static final String LEADERBOARD_KEY = "leaderboard";
    private final StringRedisTemplate redisTemplate;

    public boolean setUserScore(String userId, int score) {
        var zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);
        return true;
    }

    public Long getUserRanking(String userId) {
        var zSetOps = redisTemplate.opsForZSet();
        return zSetOps.reverseRank(LEADERBOARD_KEY, userId);
    }

    public List<String> getTopRank(int limit) {
        var zSetOps = redisTemplate.opsForZSet();
        Set<String> range = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit-1);
        return new ArrayList<>(Objects.requireNonNull(range));
    }
}
