package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;


public interface ArticleRepository extends JpaRepository<Article,Long>{
	Page<Article> findByDescriptionContains(String description, Pageable pageable);
	Page<Article> findByCategoryId(Long categoryId, Pageable pageable);
	Page<Article> findByCategoryIdAndDescriptionContains(Long categoryId,String description, Pageable pageable);
	//1.5 bis TODO error Executing an update/delete query => may not be possible
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE Article A SET A.description = :#{#x.description}, A.brand = :#{#x.brand}, A.price = :#{#x.price} WHERE A.id = :#{#x.id}")
//	public void oioioi(@Param("x") Article atr);
}
