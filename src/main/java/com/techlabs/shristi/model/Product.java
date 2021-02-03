package com.techlabs.shristi.model;

public class Product {

	private long id;
	private String name;
	private String category;
	private double price;
	private String brand;
	
	public Product(long id, String name, String category, double price, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.brand = brand;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Product() {
		super();
	}
}
