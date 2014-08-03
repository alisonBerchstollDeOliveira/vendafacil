package com.mmidgard.vendas.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1329706785233549101L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String pathPhoto;
	@DatabaseField
	private String name;
	@DatabaseField
	private String phone;
	@DatabaseField
	private String cellPhone;
	@DatabaseField
	private String city;
	@DatabaseField
	private String uf;
	@DatabaseField
	private String number;
	@DatabaseField
	private String complement;
	@DatabaseField
	private String street;
	@DatabaseField
	private String obs;
//	@DatabaseField
//	private Collection<Sale> sales;

	public Customer(String name, String city, String uf) {
		this.name = name;
		this.city = city;
		this.uf = uf;
	}

	public Customer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

//	public Collection<Sale> getSales() {
//		return sales;
//	}
//
//	public void setSales(Collection<Sale> sales) {
//		this.sales = sales;
//	}
	
	

}
