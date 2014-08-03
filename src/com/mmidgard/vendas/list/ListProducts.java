package com.mmidgard.vendas.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
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

import com.mmidgard.vendas.AdapterListProducts;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.dao.ProductDAO;
import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.entity.Product;
import com.mmidgard.vendas.news.NewProduct;

public class ListProducts extends Activity {

	private AdapterListProducts adapterList;
	private ListView listview;
	private List<Product> listProduct;
	private Button newProduct;
	private Spinner order;
	private boolean firstFilter = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_products);

		listview = (ListView)findViewById(R.id.list_products);
		newProduct = (Button)findViewById(R.id.new_);
		newProduct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListProducts.this, NewProduct.class);
				startActivity(i);
				finish();
			}
		});

		updateList();
		sortList();
	}

	private void sortList() {
		List<String> ordem = new ArrayList<String>();
		ordem.add("A→Z");
		ordem.add("Z→A");

		order = (Spinner)findViewById(R.id.products_order);
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
						Collections.sort(listProduct, Product.getComparatorName());
						adapterList.notifyDataSetChanged();
					} else {
						Collections.reverse(listProduct);
						adapterList.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

	}

	private void updateList() {
		listProduct = new ArrayList<Product>();
		ProductDAO cdao = new ProductDAO(getApplicationContext());
		listProduct = cdao.getAll();
		Collections.sort(listProduct, Product.getComparatorName());
		adapterList = new AdapterListProducts(ListProducts.this, listProduct);
		listview.setAdapter(adapterList);
	}

}
