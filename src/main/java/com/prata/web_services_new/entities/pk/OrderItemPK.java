package com.prata.web_services_new.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.prata.web_services_new.entities.Pedido;
import com.prata.web_services_new.entities.Product;

// Esta classe é uma classe auxiliar construida para resolver o problema de orientação a objetos que não possui foreign key.
// Faz referencia para Produto e Pedido. Não terá construtores, mas terá getters e setters. hashcod e equals terão as chaves das duas entidades
@Embeddable
public class OrderItemPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Pedido order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Pedido getOrder() {
		return order;
	}
	public void setOrder(Pedido order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
}
