package com.prata.web_services_new.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prata.web_services_new.entities.Pedido;
import com.prata.web_services_new.services.OrderService;

@RestController
@RequestMapping(value="/pedidos")
public class OrderResource {
   
	@Autowired
	private OrderService service;   //injeção de depência. Só que pra isso funcionar, a classe UserService precisa estar registrada como um componente do Spring
	
	@GetMapping
	public ResponseEntity<List<Pedido>> FindAll() {
		List<Pedido> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping(value="/{id}")    // para indicar que minha requisição vai aceitar um id dentro da URL
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj) ;
	}
	

}
