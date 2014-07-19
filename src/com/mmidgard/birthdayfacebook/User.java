package com.mmidgard.birthdayfacebook;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 198661379875270618L;
	@DatabaseField(id = true)
	private String id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String birthdayDate;
	@DatabaseField
	private String age;
	
	public User()
	{
	}

	public User(String name, String birthdayDate, String age) {
		this.name = name;
		this.birthdayDate = birthdayDate;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
