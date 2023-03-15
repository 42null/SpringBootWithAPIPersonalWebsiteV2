package edu.wctc.wholesale.exception;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String resource, String parm, String value){
		super(String.format("%s not found for %s: %s", resource, parm, value));
	}
}
