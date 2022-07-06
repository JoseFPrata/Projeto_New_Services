package com.prata.web_services_new.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity       //MAPEAMENTO
@Table(name = "Tb_Product") // apenas para utilizar o mesmo nome que o instrutor está usando  //MAPEAMENTO
public class Product implements Serializable{
   private static final long serialVersionUID = 1L;
   
   @Id         //MAPEAMENTO
   @GeneratedValue(strategy = GenerationType.IDENTITY)   //MAPEAMENTO
   private Long id;
   private String name;
   private String description;
   private Double price;
   private String imgUrl;
   
   @Transient   // Provisório para impedir que o JPA tente traduzir essa ligação muitos Produtos em categorias e muitas categorias em produtos
   // Associando com categoria
   private Set<Category> categories = new HashSet<>();  // como um produto não pode pertencer a duas categorias, ao invés de lista usamos Set
   // Instanciamos para garantir que nossa coleção não começe nula mesmo vazia. Como o Set é uma interface ele não pode ser instanciado
   //Então tem que usar HashSet<>() que é correspondente a essa interface
   
   
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

   
   // Não colocar a colocação nos construtor com argumentos pois ela já instanciada lá em cima
   

}
