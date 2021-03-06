package com.prata.web_services_new.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
   
	@Autowired
	private UserService service;   //injeção de depência. Só que pra isso funcionar, a classe UserService precisa estar registrada como um componente do Spring
	
	@GetMapping
	public ResponseEntity<List<Usuario>> FindAll() {
		List<Usuario> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping(value="/{id}")    // para indicar que minha requisição vai aceitar um id dentro da URL
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj) ;
	}
	
	// vamos usar o @PostMapping pois é inserção de usuário
	@PostMapping
	public ResponseEntity<Usuario> insert  (@RequestBody Usuario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj) ;   //
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Usuario> update(@PathVariable Long id,@RequestBody Usuario obj ){
		obj =service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
