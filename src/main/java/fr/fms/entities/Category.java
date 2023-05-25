//package fr.fms.entities;
//
//import java.io.Serializable;
//import java.util.Collection;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Entity
//@Data @NoArgsConstructor @AllArgsConstructor @ToString
//public class Category implements Serializable {
//	private static final long serialVersionUID = 1L;
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//	private String name;
//	
//	@OneToMany(mappedBy = "category")
//	private Collection<Article> articles;
//	
//	public Category(String name) {
//		this.name = name;
//	}
//	
//	public Category(String name, Collection<Article> articles) {
//		this.name = name;
//		this.articles = articles;
//	}
//
//	public Category() {
//		
//	}
//	
//	public Collection<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(Collection<Article> articles) {
//		this.articles = articles;
//	}
//
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	@Override
//	public String toString() {
//		return "Category [id=" + id + ", name=" + name + "]";
//	}
//	
//	
//}
