package com.prata.web_services_new.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity // MAPEAMENTO
@Table(name = "Tb_payment") // apenas para utilizar o mesmo nome que o instrutor está usando //MAPEAMENTO
public class Payment implements Serializable{
private static final long serialVersionUID = 1L;

 @Id // MAPEAMENTO
 @GeneratedValue(strategy = GenerationType.IDENTITY) // MAPEAMENTO
 private Long id;
 private Instant moment;
 
 @OneToOne
 @MapsId
 private Pedido order;
 
 public Payment() {
	 
	 
 }

public Payment(Long id, Instant moment, Pedido order) {
	super();
	this.id = id;
	this.moment = moment;
	this.order = order;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Instant getMoment() {
	return moment;
}

public void setMoment(Instant moment) {
	this.moment = moment;
}

public Pedido getOrder() {
	return order;
}

public void setOrder(Pedido order) {
	this.order = order;
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
	Payment other = (Payment) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
 
 
 
}
