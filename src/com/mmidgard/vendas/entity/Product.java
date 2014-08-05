package com.mmidgard.vendas.entity;

import java.io.Serializable;
import java.util.Comparator;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "provider")
public class Product implements Serializable {

	private static final long serialVersionUID = 198661379875270618L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField 
	private String pathPhoto;
	@DatabaseField
	private String code;
	@DatabaseField
	private String name;
	@DatabaseField
	private String description;
	@DatabaseField
	private String costPrice;
	@DatabaseField
	private String stock;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Category category;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Provider provider;

	public Product() {
	}

	public Product(String name, String stock, String value) {
		this.name = name;
		this.stock = stock;
		this.costPrice = value;
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

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	//
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public static Comparator<Product> getComparatorName() {
		return new Comparator<Product>() {
			@Override
			public int compare(Product c1, Product c2) {
				return c1.getName().compareToIgnoreCase(c2.getName());
			}
		};
	}

}
