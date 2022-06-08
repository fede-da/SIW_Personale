package it.uniroma3.siw.ProgettoSIW.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.uniroma3.siw.ProgettoSIW.exception.PostException;

@ControllerAdvice
public class PostExceptionAdvice {
	@ResponseBody
	@ExceptionHandler(PostException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	
	String postNotExecutable(PostException ex) {
		return ex.getMessage();
	}

}
