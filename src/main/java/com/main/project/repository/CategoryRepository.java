package com.main.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	List<Category> findByName(@Param("name") String name);
	
	@Query(value = "SELECT C.* FROM CATEGORY C INNER JOIN PRODUCT P ON C.PRODUCT_ID = P.ID WHERE C.PRODUCT_ID = ?1", nativeQuery = true)
	List<Category> findByProductId(@Param("product_id") Long product_id);
}