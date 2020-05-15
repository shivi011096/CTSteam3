package com.cts.project.theater.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TheaterNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 100L;

	public TheaterNotFoundException() 
    {
        super();
    }

    public TheaterNotFoundException(String message) 
    {
        super(message);
    }
    
    public TheaterNotFoundException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
