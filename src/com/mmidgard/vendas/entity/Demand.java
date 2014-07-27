package com.mmidgard.vendas.entity;

import java.util.Date;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "demand")
public class Demand {

	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private List<Product> products;
	@DatabaseField
	private Customer customer;
	@DatabaseField
	private Date date;
	@DatabaseField
	private double discount;
	@DatabaseField
	private double priceFinal;

	public Demand() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPriceFinal() {
		return priceFinal;
	}

	public void setPriceFinal(double priceFinal) {
		this.priceFinal = priceFinal;
	}

}
