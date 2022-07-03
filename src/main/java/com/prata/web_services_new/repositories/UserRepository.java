package com.prata.web_services_new.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prata.web_services_new.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	

}
