package com.gfmnow.appointmentscheduling.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DataNotFound.class)
	public ResponseEntity<GlobalResponse> handleResourceNotFound(DataNotFound ex) {
		GlobalResponse error = new GlobalResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public GlobalResponse handleValidationErrors(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.joining(", "));
		return new GlobalResponse(400, message, LocalDateTime.now());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<GlobalResponse> handleBadRequest(BadRequestException ex) {
		GlobalResponse response = new GlobalResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	 @ExceptionHandler(NullPointerException.class)
	    public ResponseEntity<GlobalResponse> handleNullPointerException(NullPointerException ex) {
	        GlobalResponse response = new GlobalResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "A null value was encountered: " + ex.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
