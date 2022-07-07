package com.prata.web_services_new.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // MAPEAMENTO
@Table(name = "Tb_Product") // apenas para utilizar o mesmo nome que o instrutor está usando //MAPEAMENTO
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // MAPEAMENTO
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MAPEAMENTO
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	// Associando com categoria, mapeamento de Product com categoria com JPA.
	// JoinTable
	// ATENÇÃO: Esta anotação poderia ser feita na classe da entidade Category,
	// porém ai o inverse seria o Product
	private Set<Category> categories = new HashSet<>(); // como um produto não pode pertencer a duas categorias, ao
														// invés de lista usamos Set
	// Instanciamos para garantir que nossa coleção não começe nula mesmo vazia.
	// Como o Set é uma interface ele não pode ser instanciado
	// Então tem que usar HashSet<>() que é correspondente a essa interface
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();    // Usa-se Set e não List porque não serão permitidas repetições

	public Product() {

	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	
	@JsonIgnore     // Para não ficar em loop pois um Pedido tem muitos produtos e ficaria em loop com Jackson na apresentação do Json
	                // pois pretende-se buscar apenas o produto e suas categorias
	public Set<Pedido> getOrders(){
		Set<Pedido> set = new HashSet<>();
		for (OrderItem x: items) {
			set.add(x.getOrder());
		}
			
		return set;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Não colocar a colocação nos construtor com argumentos pois ela já instanciada
	// lá em cima

}
