package com.wellid.exercise.exceptions.config;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellid.exercise.common.ApplicationContextUtils;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
public class AppError
{
    private final AppErrorCode code;

    @JsonIgnore
    private final String messageId;

    @JsonIgnore
    private final String[] messageArguments;

    public AppError(AppErrorCode code, String messageId) {
        this.code = code;
        this.messageId = messageId;
        this.messageArguments = new String[0];
    }

    public AppError(AppErrorCode code, String messageId, String[] messageArguments) {
        this.code = code;
        this.messageId = messageId;
        this.messageArguments = messageArguments;
    }

    @JsonGetter("message")
    public String getResolvedMessage() {
        var messageSource = ApplicationContextUtils.getApplicationContext().getBean(MessageSource.class);
        return messageSource.getMessage(this.messageId, this.messageArguments, LocaleContextHolder.getLocale());
    }
}
