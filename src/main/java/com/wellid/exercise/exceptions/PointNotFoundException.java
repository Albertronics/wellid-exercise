package com.wellid.exercise.exceptions;

import com.wellid.exercise.exceptions.config.AppError;
import com.wellid.exercise.exceptions.config.AppErrorCode;
import com.wellid.exercise.exceptions.config.AppException;
import org.springframework.http.HttpStatus;

public class PointNotFoundException extends AppException
{
    public PointNotFoundException(String identifier) {
        super(HttpStatus.NOT_FOUND);

        var error = new AppError(
            AppErrorCode.POINT_NOT_FOUND,
            AppErrorCode.POINT_NOT_FOUND.getMessageId(),
            new String[]{ identifier }
        );

        this.addError(error);
    }
}
