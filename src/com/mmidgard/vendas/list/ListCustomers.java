package com.mmidgard.vendas.list;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.mmidgard.vendas.AdapterListCustomers;
import com.mmidgard.vendas.GlobalActivity;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.dao.CustomerDAO;
import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.news.NewCustomer;

public class ListCustomers extends GlobalActivity {

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
				startActivityForResult(i, 1);
			}
		});

		updateList();
	}

	private void updateList() {
		listCustomers = new ArrayList<Customer>();
		CustomerDAO cdao = new CustomerDAO(getApplicationContext());
		listCustomers = cdao.getAll();

		adapterList = new AdapterListCustomers(ListCustomers.this, listCustomers);
		listview.setAdapter(adapterList);
	}

}
