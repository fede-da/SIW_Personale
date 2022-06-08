package it.uniroma3.siw.ProgettoSIW.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.uniroma3.siw.ProgettoSIW.exception.NftNotFoundException;

@ControllerAdvice
public class NftNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(NftNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String nftNotFoundHandler(NftNotFoundException ex) {
		return ex.getMessage();
	}

}
