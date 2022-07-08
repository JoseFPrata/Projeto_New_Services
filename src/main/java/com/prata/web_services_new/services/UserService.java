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
	
	// operação para listar usuários
	public List<Usuario> findAll(){
		
		return repository.findAll();
	}
	
	
	//operação para encontrar por id um determinado usuário
	public Usuario findById(Long id) {     // para retornar por id de usuário
		Optional<Usuario>  obj = repository.findById(id);
		return obj.get();
	}
	
	
	// operação para salvar no banco de dados um determinado usuário
	public Usuario insert(Usuario obj) {
		return repository.save(obj); // esse método repository.save por padrão já salva o objeto então basta colocar o return
	}
	

}
