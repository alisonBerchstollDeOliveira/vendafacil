package com.mmidgard.vendas.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

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
	private Spinner order;
	private boolean firstFilter = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_customers);
	}

	@Override
	protected void onStart() {
		super.onStart();
		listview = (ListView)findViewById(R.id.list_customers);
		newCustomer = (Button)findViewById(R.id.new_);
		newCustomer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListCustomers.this, NewCustomer.class);
				startActivityForResult(i, 1);
			}
		});

		customersList();
		sortList();
	}

	private void sortList() {
		List<String> ordem = new ArrayList<String>();
		ordem.add("A→Z");
		ordem.add("Z→A");

		order = (Spinner)findViewById(R.id.customers_order);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ordem);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		order.setAdapter(adapter);
		order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				if (firstFilter)
					firstFilter = false;
				else {
					if (posicao == 0) {
						Collections.sort(listCustomers, Customer.getComparatorName());
						adapterList.notifyDataSetChanged();
					} else {
						Collections.reverse(listCustomers);
						adapterList.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

	}

	private void customersList() {
		listCustomers = new ArrayList<Customer>();
		CustomerDAO cdao = new CustomerDAO(getApplicationContext());
		listCustomers = cdao.getAll();
		Collections.sort(listCustomers, Customer.getComparatorName());
		adapterList = new AdapterListCustomers(ListCustomers.this, listCustomers);
		listview.setAdapter(adapterList);
	}

}
