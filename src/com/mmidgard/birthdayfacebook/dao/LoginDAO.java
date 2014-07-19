package com.mmidgard.birthdayfacebook.dao;

import com.mmidgard.birthdayfacebook.Login;

import android.content.Context;

public class LoginDAO extends GenericDAO<Login> {

	public LoginDAO(Context context) {
		super(context, Login.class);
	}
}
