package com.example.redis;

import com.example.redis.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/setScore")
    public boolean setScore(@RequestParam String userId, @RequestParam int score) {
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/getRank")
    public Long getUserRank(@RequestParam String userId) {
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/getTopRanks")
    public List<String> getTopRanks() {
        return rankingService.getTopRank(3);
    }
}
