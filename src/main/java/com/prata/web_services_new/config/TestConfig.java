package com.prata.web_services_new.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
// Essa classe incialmente serve para fazer o Database Seeding ou seja popular o bancoprivate userRepository
// essa classe tem uma relação de dependencia do serviço de outra classe no caso UserRepository
// Mas essa injeção de dependência tem que ser fraca ou seja desacoplada
// geralmente o framework tem injeção de dependência implicita.
// veja abaixo public UserRepository
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired   // isso fará com que o Spring entenda que é uma injeção de dependência 
	public UserRepository userRepository;  // injeção de dependência

	@Override
	public void run(String... args) throws Exception {   // esse método foi implementado pelo Spring 
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		
	}
	
	

}

// está funcionando perfeitamente, O MAPEAMENTO JPA  e a INSTANCIAÇÃO DO BANCO DE DADOS