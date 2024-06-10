package com.main.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT P.* FROM PRODUCT P INNER JOIN CATEGORY C ON C.PRODUCT_ID = P.ID GROUP BY P.ID HAVING COUNT (C.ID) = ?1", nativeQuery = true)	
	List<Product> findProductsByCategoryCount(@Param("category_count") Integer categoryCount);

	@Query(value = "SELECT P.* FROM PRODUCT P INNER JOIN CATEGORY C ON C.PRODUCT_ID = P.ID GROUP BY C.NAME,P.ID", nativeQuery = true)
	List<Product> listProductsGroupedByCategory();

	@Query(value = "SELECT * FROM PRODUCT P ORDER BY P.PRICE", nativeQuery = true)	
	List<Product> listProductsOrderedByPrice();
}