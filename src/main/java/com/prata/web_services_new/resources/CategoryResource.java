package com.prata.web_services_new.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prata.web_services_new.entities.Category;
import com.prata.web_services_new.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
   
	@Autowired
	private CategoryService service;   //injeção de depência. Só que pra isso funcionar, a classe CategoryService precisa estar registrada como um componente do Spring
	
	@GetMapping
	public ResponseEntity<List<Category>> FindAll() {
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping(value="/{id}")    // para indicar que minha requisição vai aceitar um id dentro da URL
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj) ;
	}
	

}
