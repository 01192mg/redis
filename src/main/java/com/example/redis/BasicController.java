package com.example.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BasicController {
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/set")
    public String set(@RequestParam String input) {
        var ops = redisTemplate.opsForValue();
        ops.set("data", input);
        return "complete";
    }

    @GetMapping("/get")
    public String get() {
        var ops = redisTemplate.opsForValue();
        return ops.get("data");
    }
}
