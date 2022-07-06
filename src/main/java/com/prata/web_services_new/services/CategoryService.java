package com.prata.web_services_new.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prata.web_services_new.entities.Category;
import com.prata.web_services_new.repositories.CategoryRepository;

//@Component     // registra essa classe como um componente do Spring para poder ser injetado automaticamente
// Poderia ser também @Repository
@Service // Preferivel esta pois é um serviço
public class CategoryService {

	@Autowired
	private CategoryRepository repository; // injeção de dependência do CategoryRepository

	public List<Category> findAll() {

		return repository.findAll();
	}

	public Category findById(Long id) { // para retornar por id de usuário
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

}
