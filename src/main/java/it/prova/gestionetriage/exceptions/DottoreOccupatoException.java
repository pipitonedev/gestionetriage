package it.prova.gestionetriage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DottoreOccupatoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DottoreOccupatoException(String message) {
		super(message);
	}

}
