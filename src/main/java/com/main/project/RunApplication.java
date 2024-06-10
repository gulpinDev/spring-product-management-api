package com.main.project;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.project.model.Product;
import com.main.project.service.ProductService;

@SpringBootApplication
public class RunApplication {
	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};

			URL url = new URL("http://fakestoreapi.com/products");
			URLConnection urlc = url.openConnection();
			urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
					+ "Windows NT 5.1; en-US; rv:1.8.0.11) ");
			
			InputStream inputStream = urlc.getInputStream();

			try {
				List<Product> products = mapper.readValue(inputStream,typeReference);
				productService.save(products);
				System.out.println("Produtos adicionados no arranque");
			} catch (IOException e){
				System.out.println("Problema ao guardar os produtos: " + e.getMessage());
			}
		};
	} 
}
