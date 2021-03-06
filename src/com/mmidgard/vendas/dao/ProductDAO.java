package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Product;

public class ProductDAO extends GenericDAO<Product> {

	public ProductDAO(Context context) {
		super(context, Product.class);
	}
	
	public int getCount() {
		return getAll().size() > 0 ? getAll().size() : 0;
	}

}
