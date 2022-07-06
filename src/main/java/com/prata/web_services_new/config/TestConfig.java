package com.prata.web_services_new.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.prata.web_services_new.entities.Category;
import com.prata.web_services_new.entities.Pedido;
import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.entities.enums.OrderStatus;
import com.prata.web_services_new.repositories.CategoryRepository;
import com.prata.web_services_new.repositories.OrderRepository;
import com.prata.web_services_new.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	/*private static final OrderStatus PAID = null;

	private static final OrderStatus WAITING_PAYMENT = null;

	private static final OrderStatus CANCELED = null;  */

	@Autowired   // isso fará com que o Spring entenda que é uma injeção de dependência 
	private UserRepository userRepository;  // injeção de dependência
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	

	@Override
	public void run(String... args) throws Exception {   // esse método foi implementado pelo Spring 
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		

	    Pedido p1 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.PAID,u1);
		Pedido p2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Pedido p3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELAD,u1); 
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(p1,p2,p3));
	}
	
	

}

// está funcionando perfeitamente, O MAPEAMENTO JPA  e a INSTANCIAÇÃO DO BANCO DE DADOS