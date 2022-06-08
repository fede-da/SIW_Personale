package it.uniroma3.siw.ProgettoSIW.exception;

public class PostException extends RuntimeException {
	
	public PostException(String type){
		super("Could not execute http POST on  " + type );
	}

}
