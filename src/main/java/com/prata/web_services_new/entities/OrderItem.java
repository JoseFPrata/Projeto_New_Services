package com.prata.web_services_new.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
//Nesta classe não colocar id para nenhuma das entidades fortes, pois este virá do id composto na entidade associativa OrderItemPK
import com.prata.web_services_new.entities.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId   // Aqui foi colocada está anotação pois mapea para uma chave composta dessa classe OrderItemPK
	private OrderItemPK id = new OrderItemPK(); // Sempre que for inicializar uma classe com id composto, TEM QUE INSTANCIA-LA AQUI MESMO
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		
	}

	//Pedido order e Product product relacionados na classe OrderItemPK foram incluidos depois neste construtor
	public OrderItem(Pedido order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);   // instanciando passando também a parte da chave do Pedido
		id.setProduct(product); // instanciando passando também a parte da chave do Pedido
		this.quantity = quantity;
		this.price = price;
	}

	// Aqui construimos manualmente os gets e setters das partes da chave de Pedido e Product
	// Na platafforma Java Enterprise o que vale é o método get, não a declaração de associação
	// private OrderItemPK id = new OrderItemPK(); Porisso o @JsonIgnore ficará aqui
	@JsonIgnore  
	public Pedido getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Pedido order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	// aqui foram construidos os campos da classe
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
