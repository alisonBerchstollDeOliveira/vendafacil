package com.mmidgard.vendas.news;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.mmidgard.vendas.AdapterListSaleCustomer;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.entity.Customer;

public class NewSale extends Activity {

	private AdapterListSaleCustomer adapterList;
	private ListView listview;
	private List<Customer> listCustomers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_sale);

		listview = (ListView)findViewById(R.id.list_customers);

		updateList();
	}

	private void updateList() {
		listCustomers = new ArrayList<Customer>();
		Customer u;
		u = new Customer("Lucas Rafagnin", "Francisco Beltrão", "PR");
		listCustomers.add(u);
		u = new Customer("Ronaldo Simões", "Rio de Janeiro", "RJ");
		listCustomers.add(u);
		u = new Customer("Alexandre Silva", "São Paulo", "SP");
		listCustomers.add(u);

		adapterList = new AdapterListSaleCustomer(NewSale.this, listCustomers);
		listview.setAdapter(adapterList);
	}

}
