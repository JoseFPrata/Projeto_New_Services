package com.prata.web_services_new.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prata.web_services_new.entities.Product;
import com.prata.web_services_new.repositories.ProductRepository;

//@Component     // registra essa classe como um componente do Spring para poder ser injetado automaticamente
// Poderia ser também @Repository
@Service   // Preferivel esta pois é um serviço
public class ProductService {
	
	@Autowired
	private ProductRepository repository; // injeção de dependência do ProductRepository
	
	public List<Product> findAll(){
		
		return repository.findAll();
	}
	
	
	public Product findById(Long id) {     // para retornar por id de usuário
		Optional<Product>  obj = repository.findById(id);
		return obj.get();
	}
	

}
