package com.prata.web_services_new.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prata.web_services_new.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	

}
