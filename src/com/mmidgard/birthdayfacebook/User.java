package com.mmidgard.birthdayfacebook;

public class User {
	
	private String name;
	private String birthdayDate;
	private String age;
	
	public User(String name, String birthdayDate, String age)
	{
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
