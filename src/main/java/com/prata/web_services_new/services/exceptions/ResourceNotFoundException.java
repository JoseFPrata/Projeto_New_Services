package com.prata.web_services_new.services.exceptions;

// para tratar erro quando não encontrar o id

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Recurso não enconctrado para o usuário de Id número: " + id);
		
	}
	

}
