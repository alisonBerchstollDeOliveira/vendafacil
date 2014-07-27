package com.mmidgard.vendas.dao;

import android.content.Context;

import com.mmidgard.vendas.entity.Demand;

public class DemandDAO extends GenericDAO<Demand> {

	public DemandDAO(Context context) {
		super(context, Demand.class);
	}
}
