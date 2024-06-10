package com.main.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.project.model.Category;
import com.main.project.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(path="/listAll")
	public @ResponseBody List<Category> listAll() {
		return categoryRepository.findAll();
	}

	@PostMapping
	public @ResponseBody String addNewCategory (@RequestBody Category category) {   

		List<Category> categoriesList = categoryRepository.findByName(category.getName());

		//se a categoria existe, nao e adicionada
		if (!categoriesList.isEmpty()) {
			return "Categoria ja existe";
		}

		else {
			categoryRepository.save(category);
			return "Categoria " + category.getName() + " adicionada";
		}
	}
}