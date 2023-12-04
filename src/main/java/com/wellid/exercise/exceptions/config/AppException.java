package com.wellid.exercise.exceptions.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Getter
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "suppressed", "message"})
public abstract class AppException extends RuntimeException implements Serializable
{
    @Serial
    private static final long serialVersionUID = 42L;

    protected final Locale locale = LocaleContextHolder.getLocale();

    private final Instant timestamp = Instant.now();
    private final Integer status;
    private final List<AppError> errors = new ArrayList<>();

    protected AppException(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }

    public AppException addError(AppError error) {
        this.errors.add(error);
        return this;
    }

    public AppException addErrors(Collection<AppError> errors) {
        this.errors.addAll(errors);
        return this;
    }
}
