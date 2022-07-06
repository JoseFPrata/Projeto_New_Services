package com.prata.web_services_new.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prata.web_services_new.entities.Product;
import com.prata.web_services_new.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductResource {
   
	@Autowired
	private ProductService service;   //injeção de depência. Só que pra isso funcionar, a classe ProductService precisa estar registrada como um componente do Spring
	
	@GetMapping
	public ResponseEntity<List<Product>> FindAll() {
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping(value="/{id}")    // para indicar que minha requisição vai aceitar um id dentro da URL
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj) ;
	}
	

}
