package com.main.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.main.project.model.Category;
import com.main.project.model.Product;
import com.main.project.repository.CategoryRepository;
import com.main.project.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductService (ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public String save (List<Product> products) {

		productRepository.saveAll(products);
		
		String message = "";
		for (Product p: products) {
			message += "Produto " + p.getTitle() + " adicionado \n";
		}

		Category category = null;

		for (Product p : products) {
			
			//limpa lista de categorias de cada produto para nao repetir categorias novas
			if (p.getId() != null) {//so acontece quando estamos a ATUALIZAR um produto
				for (Category cat : categoryRepository.findByProductId(p.getId())) {
				categoryRepository.delete(cat);
				}
			}
			for (String c : Arrays.asList(p.getCategory().split("\\s*,\\s*"))) {
				category = new Category();
				category.setName(c);

				//associa produto a categoria e atualiza as categorias
				category.setProduct(p);
				categoryRepository.save(category);
			}
		}
		
		return message;
	}

	public List<Product> listAll() {
		return productRepository.findAll();
	}

	public List<Product> listAllByCategoryCount(Integer category_count) {
		if (category_count != null) {
			return productRepository.findProductsByCategoryCount(category_count);
		}
		else {
			return productRepository.findAll();
		}
	}

	public List<Product> groupByCategory() {
		return productRepository.listProductsGroupedByCategory();
	}

	public List<Product> orderByPrice() {
		return productRepository.listProductsOrderedByPrice();
	}

	public String update(Product product, Long id) {

		if (productRepository.existsById(id)) {
			List<Product> productToList = new ArrayList<Product>();
			productToList.add(product);
			
			product.setId(id);
			save(productToList);
			return "Produto id = " + id + " atualizado";
		}
		else {
			return "Produto id = " + id + " nao existe";
		}
	}

}
