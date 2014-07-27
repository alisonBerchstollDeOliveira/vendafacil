package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Customer;

public class CustomerDAO extends GenericDAO<Customer> {

	public CustomerDAO(Context context) {
		super(context, Customer.class);
	}
}
