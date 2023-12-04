package com.wellid.exercise.exceptions;

import com.wellid.exercise.exceptions.config.AppError;
import com.wellid.exercise.exceptions.config.AppErrorCode;
import com.wellid.exercise.exceptions.config.AppException;
import org.springframework.http.HttpStatus;

public class InvalidLinesValueException extends AppException
{
    public InvalidLinesValueException() {
        super(HttpStatus.BAD_REQUEST);

        var error = new AppError(
            AppErrorCode.INVALID_LINES_VALUE,
            AppErrorCode.INVALID_LINES_VALUE.getMessageId()
        );

        this.addError(error);
    }
}
