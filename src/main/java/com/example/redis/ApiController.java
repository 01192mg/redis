package com.example.redis;

import com.example.redis.dto.UserProfile;
import com.example.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiController {
    private final UserService userService;

    @GetMapping("/users/{userId}/profiles")
    public UserProfile getUserProfile(@PathVariable String userId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        UserProfile userProfile = userService.getUserProfile(userId);

        stopWatch.stop();
        log.info("{} seconds", stopWatch.getTotalTimeSeconds());

        return userProfile;
    }
}
