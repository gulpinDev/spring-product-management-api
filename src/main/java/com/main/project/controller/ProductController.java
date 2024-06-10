package com.main.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.project.model.Product;
import com.main.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path="/listAll")
	public @ResponseBody List<Product> listAll() {
		return productService.listAll();
	}

	@GetMapping(path= {"/listByCategoryCount/{category_count}","/listByCategoryCount"})
	public @ResponseBody List<Product> listAllByCategoryCount(@PathVariable(required = false) Integer category_count) {
		return productService.listAllByCategoryCount(category_count);
	}

	@GetMapping(path="/groupByCategory")
	public @ResponseBody List<Product> groupByCategory() {
		return productService.groupByCategory();
	}

	@GetMapping(path="/orderByPrice")
	public @ResponseBody List<Product> orderByPrice() {
		return productService.orderByPrice();
	}

	@PutMapping("/{id}")
	public String update(@RequestBody Product product, @PathVariable Long id) {
		return productService.update(product, id);
	}

	//usado tambem no arranque do servidor para popular a BD com os dados JSON fornecidos
	@PostMapping
	public @ResponseBody String addProducts (@RequestBody List<Product> products) {
		return productService.save(products);
	}
}