package com.prata.web_services_new.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.repositories.UserRepository;
import com.prata.web_services_new.services.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));    // foi trocado método get() por este após criar os tratamentos de exceções próprios
	}
	
	
	// operação para salvar no banco de dados um determinado usuário
	public Usuario insert(Usuario obj) {
		return repository.save(obj); // esse método repository.save por padrão já salva o objeto então basta colocar o return
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id); // esse método deleta um usuário
	}

	public Usuario update(Long id, Usuario obj){
		Usuario entity =repository.getOne(id);  // getOne é mais eficiente que o findBy, porque apenas traz o objeto para depois esse ser inserido no banco
		updateData(entity,obj);
		return repository.save(entity);
		
	}


	private void updateData(Usuario entity, Usuario obj) {   //só será permitido atualizar esses campos. id e password não
		entity.setName(obj.getName());                       // esse método foi construido em razão da chamada a ele em public Usuario acima
		entity.setEmail(obj.getEmail());      
		entity.setPhone(obj.getPhone());
		
	}
	
}
