package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum Status {
    NORMAL("normal"),
    BLOCKED("blocked");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
