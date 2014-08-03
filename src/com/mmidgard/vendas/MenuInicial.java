package com.mmidgard.vendas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.mmidgard.vendas.dao.CustomerDAO;
import com.mmidgard.vendas.list.ListCustomers;
import com.mmidgard.vendas.list.ListProducts;
import com.mmidgard.vendas.news.NewCustomer;
import com.mmidgard.vendas.news.NewProduct;
import com.mmidgard.vendas.news.NewSale;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MenuInicial extends Activity {

	// Header
	private Button countCustomers;
	private Button countProducts;
	private Button countSales;

	private Button listCustomers;
	private Button newCustomer;
	private Button listProducts;
	private Button newProduct;
	private Button newSale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);

		getValues();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1)
			Crouton.makeText(MenuInicial.this, "Cliente inserido com sucesso!", Style.INFO).show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		setCountsHeader();
	}

	private void setCountsHeader() {
		countCustomers = (Button)findViewById(R.id.count_customers);
		CustomerDAO cdao = new CustomerDAO(MenuInicial.this);
		countCustomers.setText(String.valueOf(cdao.getCount()));
		cdao.close();
	}

	public void getValues() {
		listCustomers = (Button)findViewById(R.id.menu_customer);
		newCustomer = (Button)findViewById(R.id.new_customer);
		listProducts = (Button)findViewById(R.id.menu_products);
		newProduct = (Button)findViewById(R.id.new_product);
		newSale = (Button)findViewById(R.id.new_sale);

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
				startActivityForResult(i, 1);
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

		newSale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuInicial.this, NewSale.class);
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
