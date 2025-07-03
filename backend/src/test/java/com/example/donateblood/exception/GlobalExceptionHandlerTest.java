package com.example.donateblood.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
        request = new MockHttpServletRequest();
        request.setRequestURI("/api/test");
    }

    @Test
    void handleAllExceptions() {
        // Given
        Exception ex = new Exception("Test general exception");

        // When
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleAllExceptions(ex, request);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Internal Server Error", errorResponse.getError());
        assertEquals("Test general exception", errorResponse.getMessage());
        assertEquals("/api/test", errorResponse.getPath());
    }

    @Test
    void handleResourceNotFoundException() {
        // Given
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");

        // When
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleResourceNotFoundException(ex, request);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Resource Not Found", errorResponse.getError());
        assertEquals("Resource not found", errorResponse.getMessage());
        assertEquals("/api/test", errorResponse.getPath());
    }

    @Test
    void handleValidationException() {
        // Given
        ValidationException ex = new ValidationException("Validation failed");

        // When
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleValidationException(ex, request);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Validation Error", errorResponse.getError());
        assertEquals("Validation failed", errorResponse.getMessage());
        assertEquals("/api/test", errorResponse.getPath());
    }
}
