package com.gmail.jcabraham.abacus.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AbacusExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AbacusException.class)
	protected ResponseEntity<Object> handleAbacusException(AbacusException e) {
		return new ResponseEntity<>(e.getMessage(), e.getStatus());
	}
}

