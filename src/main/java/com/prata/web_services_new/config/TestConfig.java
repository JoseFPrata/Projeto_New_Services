package com.prata.web_services_new.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.prata.web_services_new.entities.Category;
import com.prata.web_services_new.entities.OrderItem;
import com.prata.web_services_new.entities.Payment;
import com.prata.web_services_new.entities.Pedido;
import com.prata.web_services_new.entities.Product;
import com.prata.web_services_new.entities.Usuario;
import com.prata.web_services_new.entities.enums.OrderStatus;
import com.prata.web_services_new.repositories.CategoryRepository;
import com.prata.web_services_new.repositories.OrderItemRepository;
import com.prata.web_services_new.repositories.OrderRepository;
import com.prata.web_services_new.repositories.ProductRepository;
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
	private OrderRepository orderRepository;        // injetando dependência
	
	@Autowired
	private CategoryRepository categoryRepository;   // injetando dependência
	
	
	@Autowired
	private ProductRepository productRepository;   // injetando dependência
	
	@Autowired
	private OrderItemRepository ordemItemRepository;   // injetando dependência
	
	
	@Override
	public void run(String... args) throws Exception {   // esse método foi implementado pelo Spring 
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		// Relacionando muitos para muitos e mappeando esse relacionamento para o banco de dados relacional
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//Salvando de novo os produtos que estão em memória com as categorias relacionadas. É assim no JPA
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		

	    Pedido pd1 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.PAID,u1);
		Pedido pd2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Pedido pd3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELAD,u1); 
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
		
		OrderItem oi1 = new OrderItem(pd1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(pd1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(pd2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(pd3, p5, 2, p5.getPrice());
		
		
		ordemItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-07-21T05:42:10Z"),pd1 );
		pd1.setPayment(pay1);     //Como era 1 para 1 não precisou criar o próprio repositório
		
	    orderRepository.save(pd1);   //aqui está salvando de novo o pedido pd1 pois foi atualizado com o Payment
	    
		
	}
	
	

}

// está funcionando perfeitamente, O MAPEAMENTO JPA  e a INSTANCIAÇÃO DO BANCO DE DADOS