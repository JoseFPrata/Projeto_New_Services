package com.prata.web_services_new.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prata.web_services_new.entities.Pedido;
import com.prata.web_services_new.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}

	public Pedido findById(Long id) {
		Optional<Pedido> obj= repository.findById(id);
		return obj.get();
	}
}
