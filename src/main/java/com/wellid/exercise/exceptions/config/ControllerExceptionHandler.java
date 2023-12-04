package com.wellid.exercise.exceptions.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler
{
    private final ObjectMapper objectMapper;

    public ControllerExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler({ConversionFailedException.class})
    public ResponseEntity<String> handleConversionFail(ConversionFailedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AppException.class })
    public void handleApiError(AppException ex, HttpServletResponse response) throws IOException
    {
        response.setStatus(ex.getStatus());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        var jsonResponse = this.objectMapper.writeValueAsString(ex);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}