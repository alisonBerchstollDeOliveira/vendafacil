package com.mmidgard.vendas.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.mmidgard.vendas.AdapterListCustomers;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.news.NewCustomer;

public class ListCustomers extends Activity {

	private AdapterListCustomers adapterList;
	private ListView listview;
	private List<Customer> listCustomers;
	private Button newCustomer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_customers);

		listview = (ListView)findViewById(R.id.list_customers);
		newCustomer = (Button)findViewById(R.id.new_);
		newCustomer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListCustomers.this, NewCustomer.class);
				startActivity(i);
				finish();
			}
		});

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

		adapterList = new AdapterListCustomers(ListCustomers.this, listCustomers);
		listview.setAdapter(adapterList);
	}

}
