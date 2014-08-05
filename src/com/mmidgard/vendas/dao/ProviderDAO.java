package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Provider;

public class ProviderDAO extends GenericDAO<Provider> {

	public ProviderDAO(Context context) {
		super(context, Provider.class);
	}
	
}
