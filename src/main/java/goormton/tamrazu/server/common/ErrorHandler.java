package goormton.tamrazu.server.common;

import static org.springframework.http.HttpStatus.*;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
		ApiResponse apiResponse = ApiResponse.fail(exception.getMessage());
		return new ResponseEntity<>(apiResponse, NOT_FOUND);
	}
}
