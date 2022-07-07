package com.prata.web_services_new.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prata.web_services_new.entities.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	

}
