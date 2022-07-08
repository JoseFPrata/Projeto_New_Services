package com.prata.web_services_new.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prata.web_services_new.entities.enums.OrderStatus;

@Entity
//@Table(name = "tb_Order")  // Isso faria com que o nome da tabela foi compilado com a alteração, pois o sql não aceita palavra reservadas
// do tipo User, Order, etc  EU PREFERI ALTERAR O NOME DA ENTIDADE. Mas após colocar 
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
 
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone ="GMT" )
	private Instant moment;
	
	private Integer orderStatus;

	@ManyToOne   // para instruir o JPA para transformar isso em uma chave estrangeira
	@JoinColumn(name = "client_id")
	@JsonIgnore   //para que não dê loop em um relacionamento 1 para muitos
	/* open-in-view na application.proprierties, permitiu ao jackson que seriaiza os dados json trazer os dados dos pedidos de cada cliente quando se
	 pesquisa por id de cliente. se estivesse spring.jpa.open-in-view=false isso não seria possível */
	private Usuario client;

	@OneToMany(mappedBy ="id.order")
	private Set<OrderItem> items = new HashSet<>();     // Porque Pedido(Order) também tem ligação de muitos pra muitos com Product. Muitas vezes será necessário saber as relações na OrdemItem
	
	@OneToOne(mappedBy = "order", cascade =CascadeType.ALL)  //Relação 1 para 1 terão o mesmo código e porisso o cascade é obrigatório
	private Payment payment;
	
	public Pedido() {

	}

	public Pedido(Long id, Instant moment, OrderStatus orderStatus, Usuario client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
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

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
		}
	}
	
	public Usuario getClient() {
		return client;
	}

	public void setClient(Usuario client) {
		this.client = client;
	}
	
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getitems(){
		return items;
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
