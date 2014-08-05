package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Category;

public class CategoryDAO extends GenericDAO<Category> {

	public CategoryDAO(Context context) {
		super(context, Category.class);
	}

}
