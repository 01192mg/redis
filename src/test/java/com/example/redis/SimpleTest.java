package com.example.redis;

import com.example.redis.service.RankingService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class SimpleTest {
    final RankingService rankingService;

    public SimpleTest(@Autowired RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Disabled
    @Test
    void insertScore() {
        IntStream.range(0, 1_000_000).parallel()
                .forEach(i -> {
                            int score = (int) (Math.random() * 1_000_000);
                            String userId = "user_" + i;
                            rankingService.setUserScore(userId, score);
                        }
                );
    }

    @Test
    void getRanks() {
        rankingService.getUserRanking("dummyCall");

        Instant before = Instant.now();
        Long userRank = rankingService.getUserRanking("user_100");
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.printf("Rank(%d) - Took %d ms%n", userRank, elapsed.getNano() / 1_000_000);

        before = Instant.now();
        List<String> topRankers = rankingService.getTopRank(10);
        elapsed = Duration.between(before, Instant.now());
        System.out.printf("Rank(%d) - Took %d ms%n", topRankers.size(), elapsed.getNano() / 1_000_000);
    }

    @Test
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            int score = (int) (Math.random() * 1_000_000); // 0~999_999
            list.add(score);
        }

        Instant before = Instant.now();
        Collections.sort(list);
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.println(elapsed.getNano() / 1_000_000 + "ms");
    }
}
