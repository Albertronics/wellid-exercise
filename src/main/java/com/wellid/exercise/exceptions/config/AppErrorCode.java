package com.wellid.exercise.exceptions.config;

import lombok.Getter;

@Getter
public enum AppErrorCode
{
    POINT_NOT_FOUND("wellid.exercise.point.NotFound.message"),
    INVALID_LINES_VALUE("wellid.exercise.line.InvalidNValue.message");

    private final String messageId;

    AppErrorCode(String messageId) {
        this.messageId = messageId;
    }

    public String get() {
        return this.name();
    }
}
