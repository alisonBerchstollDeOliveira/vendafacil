package com.mmidgard.vendas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.mmidgard.vendas.list.ListCustomers;
import com.mmidgard.vendas.list.ListProducts;
import com.mmidgard.vendas.news.NewCustomer;
import com.mmidgard.vendas.news.NewProduct;

public class MenuInicial extends FragmentActivity {

	private Button listCustomers;
	private Button newCustomer;
	private Button listProducts;
	private Button newProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);

		getValues();
	}

	public void getValues() {
		listCustomers = (Button)findViewById(R.id.menu_customer);
		newCustomer = (Button)findViewById(R.id.new_customer);
		listProducts = (Button)findViewById(R.id.menu_products);
		newProduct = (Button)findViewById(R.id.new_product);

		listCustomers.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuInicial.this, ListCustomers.class);
				startActivity(i);
			}
		});
		
		newCustomer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuInicial.this, NewCustomer.class);
				startActivity(i);
			}
		});

		listProducts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuInicial.this, ListProducts.class);
				startActivity(i);
			}
		});

		newProduct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuInicial.this, NewProduct.class);
				startActivity(i);
			}
		});



	}

	public void abrirGooglePlay(String developerName) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=" + developerName)));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=" + developerName)));
		}
	}

}
