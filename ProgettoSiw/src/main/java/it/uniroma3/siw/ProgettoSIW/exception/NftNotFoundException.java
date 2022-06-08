package it.uniroma3.siw.ProgettoSIW.exception;

public class NftNotFoundException extends RuntimeException{
	
	public NftNotFoundException(Long id){
		super("Could not find nft with id : " + id );
	}
}
