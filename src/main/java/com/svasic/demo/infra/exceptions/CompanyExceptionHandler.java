package com.svasic.demo.infra.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CompanyExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<String> handleCompanyNotFound(Exception ex) {

		return ResponseEntity.notFound().build();

	}

}
