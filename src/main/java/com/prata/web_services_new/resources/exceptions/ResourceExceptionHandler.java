package com.prata.web_services_new.resources.exceptions;
// Vai dar respostas as exceções interceptadas
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.prata.web_services_new.services.exceptions.ResourceNotFoundException;

@ControllerAdvice    // Vai interceptar os possíveis erros que possam acontecer para iniciar o primeiro tratamento
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	//AQUI TRATA O ERRO QUE OCORRE QUANDO O ITEM DELETADO POSSUI FILHOS NO BANCO E ENTÃO NÃO PODE SER DELETADO
	// ESSA CONSTRUÇÃO EVITA QUE SEJA DADO APENAS UM ERRO GENERICO PELO SPRING
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> ResourceNotFound(DatabaseException e, HttpServletRequest request){
		String error = "Não pode eliminar chave pai que contenha filhos";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	
}
