package com.sapient.fileprocessor.textprocessor.test;

import org.junit.jupiter.api.Test;
import com.sapient.fileprocessor.fileparser.impl.TxtFileParser;

public class TextProcessorTest {

	@Test
	public void testProcess() {
		
		TxtFileParser textProcessor = new TxtFileParser();
		
		String inputString = "Which says that we want all URLs within our application to be secured, requiring the role ROLE_USER to access them, we want to log in to the application using a form with username and password, and that we want a logout URL registered which will allow us to log out of the application. <http> element is the parent for all web-related namespace functionality. The <intercept-url> element defines a pattern which is matched against the URLs of incoming requests using an ant path style syntax";
		
		//textProcessor.process(inputString);
		
	}
	
}
