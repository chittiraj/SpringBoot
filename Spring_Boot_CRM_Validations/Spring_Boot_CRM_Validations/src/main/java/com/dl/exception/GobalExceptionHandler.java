package com.dl.exception;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GobalExceptionHandler extends ResponseEntityExceptionHandler {

	/// summary
	/// client is having name, cc, contactNo, email, .. etc. duplicate are not allow
	/// like twice contact and emails..
	/// For that we can write in exception and validation both can delivers. But,
	/// use the validation instand of exception.

	// if we are finding the id, is not match we use this exception.
	@ExceptionHandler(LeadNotfoundException.class)
	public ResponseEntity<?> handlerLeadNotfoundException(LeadNotfoundException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// if we are trying to insert the duplicate email or contact number.
	@ExceptionHandler(LeadCreationException.class)
	public ResponseEntity<?> handlerLeadNotfoundException(LeadCreationException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// for update process
	@ExceptionHandler(LeadUpdateException.class)
	public ResponseEntity<?> handlerLeadNotfoundException(LeadUpdateException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// user is login with same email..
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,
			WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // HTTP 409 Conflict
	}

//for same contact number  -------------------------
//	@ExceptionHandler(ContactAlreadyExistsException.class)
//	public ResponseEntity<Map<String, String>> handleContactAlreadyExistsException(ContactAlreadyExistsException ex, WebRequest request){
//		Map<String, String> response = new HashMap<>();
//		response.put("error", ex.getMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	}

	// for internal exception or anything else
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerGobalException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Validation :::
	//you're overridding the defult behaviour provied by spring
	@Override
	//handleMethodArgumentNotvaild, when we are mention the  @vaild in another class, it will see for the handleMethodArgumentNotValid.
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		//Creating a map to store field names and their correspondingerror message.
		Map<String, String> errors = new HashMap<>();
		//Getting the  validation errors(could be multiple)
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		//loop through each validation error.
		allErrors.forEach((error) ->{
			//cast the error to fielderror so we can get the specific  fieldName  (like email or password).
			String fieldName = ((FieldError) error).getField();
			//Get the validation message you sent in our, model using annotations..
			String message = error.getDefaultMessage();
			//adding the both fieldname and message 
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
