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

import com.mmidgard.vendas.AdapterListProducts;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.entity.Category;
import com.mmidgard.vendas.entity.Product;
import com.mmidgard.vendas.news.NewProduct;

public class ListProducts extends Activity {

	private AdapterListProducts adapterList;
	private ListView listview;
	private List<Product> listProduct;
	private Button newProduct;

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
	}

	private void updateList() {
		listProduct = new ArrayList<Product>();
		Product p;
		p = new Product("Nome produto1", "3", "R$ 10,00", new Category("Categoria1"));
		listProduct.add(p);
		p = new Product("Nome produto2", "25", "R$ 20,00", new Category("Categoria2"));
		listProduct.add(p);
		p = new Product("Nome produto3", "10", "R$ 15,00", new Category("Categoria2"));
		listProduct.add(p);

		adapterList = new AdapterListProducts(ListProducts.this, listProduct);
		listview.setAdapter(adapterList);
	}

}
