package com.prata.web_services_new.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prata.web_services_new.entities.Usuario;

@RestController
@RequestMapping(value="/users")
public class UserResource {
    
	@GetMapping
	public ResponseEntity<Usuario> FindAll() {
		Usuario u = new Usuario(1L,"Maria","maria@gmail.com","99999","12345");
		return ResponseEntity.ok().body(u);
	}


}
