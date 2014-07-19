package com.mmidgard.birthdayfacebook;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "login")
public class Login implements Serializable {

	private static final long serialVersionUID = 4221163507196155975L;
	@DatabaseField(id = true)
	private String id;
	@DatabaseField
	private String nome;

	public Login(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Login() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
