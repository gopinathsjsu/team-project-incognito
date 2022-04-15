package com.hotel.project.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HotelExceptionHandler extends ResponseEntityExceptionHandler {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HotelExceptionHandler.class);

	
	/*
	 * @ExceptionHandler(value = { MethodArgumentNotValidException.class }) public
	 * ResponseEntity<Object>
	 * handleInvalidInputException(MethodArgumentNotValidException ex) {
	 * 
	 * logger.error("Invalid Input Exception: ",ex.getMessage());
	 * 
	 * return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */
	 
	 @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			  HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<String> details = new ArrayList<>();
	    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	      details.add(error.getDefaultMessage());
	    }
	  //ErrorResponse error = new ErrorResponse("Validation Failed", details);
	    return new ResponseEntity<Object>("validation failed "+ details, HttpStatus.BAD_REQUEST);
	  }
	/*
	 * @ExceptionHandler(value = { Unauthorized.class })
	 * 
	 * public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
	 * 
	 * LOGGER.error("Unauthorized Exception: ",ex.getMessage());
	 * 
	 * return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */

	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> handleBusinessException(NoSuchElementException ex) {

		logger.error("no hotel with such hotel name ", ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { HotelBusinessException.class })
	public ResponseEntity<Object> handleBusinessException(HotelBusinessException ex) {

		logger.error("Business Exception: ", ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler(value = { Exception.class })
	 * 
	 * public ResponseEntity<Object> handleException(Exception ex) {
	 * 
	 * LOGGER.error("Exception: ",ex.getMessage());
	 * 
	 * return new
	 * ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { // String fieldName
	 * = ((Object) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put("fieldName", errorMessage); }); return
	 * errors; }
	 */

}
