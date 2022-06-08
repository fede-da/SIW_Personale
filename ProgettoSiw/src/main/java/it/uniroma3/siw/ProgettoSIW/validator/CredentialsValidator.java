package it.uniroma3.siw.ProgettoSIW.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.ProgettoSIW.model.Credentials;
import it.uniroma3.siw.ProgettoSIW.service.CredentialsService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Component
public class CredentialsValidator implements Validator {
	
    @Autowired
    private CredentialsService credentialsService;

    final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 8;
	
	public void validateUsername(String username,Errors error) {
		//This is a mail
		if(username.isEmpty()){
			error.rejectValue("password",   "required");
		//	return;
			} 
		if( username.length()<MIN_USERNAME_LENGTH){
			error.rejectValue("username", "size");
			//return;
			} 
		if(username.contains(" ")){
			error.rejectValue("password", "space");
			//return;
			} 
		if(!username.contains("@")){
			error.rejectValue("username",  "at");
			//return;
			}
		if (this.credentialsService.getCredentialsByUsername(username) != null)
            error.rejectValue("username", "duplicate");
		
	}
	public void validatePassword(String password, Errors error) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher intMatcher = pattern.matcher(password);
		pattern = Pattern.compile("[A-Z]");
		Matcher upperCaseMatcher = pattern.matcher(password);
		pattern = Pattern.compile("[^\\p{L}\\d]");
		Matcher specialCharsMatcher = pattern.matcher(password);
		if(password.length()< MIN_PASSWORD_LENGTH || password.length()> MAX_PASSWORD_LENGTH )
		{
			error.rejectValue("password",  "size");
			//return;
		}
		else if(password.contains(" ")) {
			error.rejectValue("password",  "space");
			//return;
			}
		if(!intMatcher.find()) {
			error.rejectValue("password",  "pwdOneNumber");
			//return;
			}
		if (!upperCaseMatcher.find()){
			error.rejectValue("password", "pwdUppercase");
			//return;
			}
		if (!specialCharsMatcher.find()){
			error.rejectValue("password",  "pwdSpecialChar");
			//return;
		} 
	}
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Credentials.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Credentials credentials = (Credentials) target;
        String username = credentials.getUsername().trim();
        String password = credentials.getPassword().trim();
		validateUsername(username,errors);
		validatePassword(password,errors);
	}

}
