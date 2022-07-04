package com.prata.web_services_new.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.repositories.UserRepository;

//@Component     // registra essa classe como um componente do Spring para poder ser injetado automaticamente
// Poderia ser também @Repository
@Service   // Preferivel esta pois é um serviço
public class UserService {
	
	@Autowired
	private UserRepository repository; // injeção de dependência do UserRepository
	
	public List<Usuario> findAll(){
		
		return repository.findAll();
	}
	
	
	public Usuario findById(Long id) {     // para retornar por id de usuário
		Optional<Usuario>  obj = repository.findById(id);
		return obj.get();
	}
	

}
