package net.the42null.personalwebsite.helpers;

public class InputSanitizer {
	public static final int DEFAULT_NUM_OF_CHARACTERS = 20;

	public static String sanitizeInput(String input) {
		return sanitizeInput(input, Integer.MAX_VALUE);
	}

	public static String sanitizeInput(String input, int numberOfCharacters){//TODO: Make optimize for large large strings (segment recursively for dealing with replacing text that would be ignored already)
		if(numberOfCharacters == -1){//Use default value
			numberOfCharacters = Integer.MAX_VALUE;
		}else if(numberOfCharacters <= 0){
			return "";
		}

		String sanitizedInput = input.trim().toLowerCase();
		//Remove non-alphanumeric characters
		sanitizedInput = sanitizedInput.replaceAll("[^a-z0-9]", "");
		//Limit # of characters
		sanitizedInput = sanitizedInput.substring(0, Math.min(sanitizedInput.length(), numberOfCharacters));

		return sanitizedInput;
	}

}
