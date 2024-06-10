package com.main.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@Lob //for strings with length > 255
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Category> categories;
	
	private String category;
	
	@Column(name = "image", nullable = false)
	private String image;
	
	@Embedded
	private Rating rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}
}