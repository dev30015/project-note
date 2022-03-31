package com.project.note.exception;

public class ResourceAlreadyExists extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9002098407708348773L;

	public ResourceAlreadyExists(String message) {
        super(message);
    }
}