package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{
//	public List<Category> findAllByOrderByName();
//	public List<Category> findAllByOrderByNameDesc();
//	//1.7 exclusion
//	public List<Category> findByNameNot(String not);
}
