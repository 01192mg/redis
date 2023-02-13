package com.example.redis.dto;

import lombok.Getter;

@Getter
public class UserProfile {
    private final String name;
    private final int age;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
