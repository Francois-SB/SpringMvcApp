package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article implements Serializable {
//	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=2,max=50, message = "la taille doit être comprise entre 2 er 50")
	private String description;
	@DecimalMin(value = "50",message = "doit être supérieur ou égal à 50")
	private double price;
	private String brand;
	@ManyToOne
	private Category category;
	
	public Article(String description,String brand, double price, Category cat) {
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = cat;
	}
	

}
