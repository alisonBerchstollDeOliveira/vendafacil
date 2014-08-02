package com.mmidgard.vendas.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 198661379875270618L;
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String description;
	@DatabaseField
	private String stock;
	@DatabaseField
	private double costPrice;
	@DatabaseField
	private String unit;
	@DatabaseField
	private Category category;
	@DatabaseField
	private Provider provider;

	// @DatabaseField
	// private Collection<Sale> sales;

	public Product() {
	}

	public Product(String name, String stock, double value, Category c) {
		this.name = name;
		this.stock = stock;
		this.costPrice = value;
		this.category = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setType(String unit) {
		this.unit = unit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// public Collection<Sale> getSales() {
	// return sales;
	// }
	//
	// public void setSales(Collection<Sale> sales) {
	// this.sales = sales;
	// }

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
