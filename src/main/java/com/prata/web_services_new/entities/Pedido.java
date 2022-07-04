package com.prata.web_services_new.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//@Table(name = "tb_Order")  // Isso faria com que o nome da tabela foi compilado com a alteração, pois o sql não aceita palavra reservadas
// do tipo User, Order, etc  EU PREFERI ALTERAR O NOME DA ENTIDADE. Mas após colocar 
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private Instant moment;

	@ManyToOne   // para instruir o JPA para transformar isso em uma chave estrangeira
	@JoinColumn(name = "client_id")
	private Usuario client;

	public Pedido() {

	}

	public Pedido(Long id, Instant moment, Usuario client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
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

	public Usuario getClient() {
		return client;
	}

	public void setClient(Usuario client) {
		this.client = client;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
