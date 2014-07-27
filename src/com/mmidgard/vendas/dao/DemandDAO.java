package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Sale;

public class DemandDAO extends GenericDAO<Sale> {

	public DemandDAO(Context context) {
		super(context, Sale.class);
	}
}
