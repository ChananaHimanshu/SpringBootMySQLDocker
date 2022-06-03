package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException exception) throws JsonProcessingException {
		HttpStatusCodeErrorMessage forbiddenResponse = new HttpStatusCodeErrorMessage();
		if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
			forbiddenResponse.setMessage(exception.getMessage());
			forbiddenResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(forbiddenResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * HTTP status code handler class for all error type messages
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	class HttpStatusCodeErrorMessage {
		private String message;
		private int statusCode;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
	}

}
