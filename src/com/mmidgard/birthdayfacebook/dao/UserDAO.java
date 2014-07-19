package com.mmidgard.birthdayfacebook.dao;

import com.mmidgard.birthdayfacebook.User;

import android.content.Context;

public class UserDAO extends GenericDAO<User> {

	public UserDAO(Context context) {
		super(context, User.class);
	}
}
