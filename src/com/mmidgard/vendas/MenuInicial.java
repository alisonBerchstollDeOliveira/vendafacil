package com.mmidgard.vendas;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.mmidgard.vendas.dao.CategoryDAO;
import com.mmidgard.vendas.dao.CustomerDAO;
import com.mmidgard.vendas.dao.ProductDAO;
import com.mmidgard.vendas.dao.ProviderDAO;
import com.mmidgard.vendas.entity.Category;
import com.mmidgard.vendas.entity.Product;
import com.mmidgard.vendas.entity.Provider;
import com.mmidgard.vendas.list.ListCustomers;
import com.mmidgard.vendas.list.ListProducts;
import com.mmidgard.vendas.news.NewCustomer;
import com.mmidgard.vendas.news.NewProduct;
import com.mmidgard.vendas.news.NewSale;

public class MenuInicial extends GlobalActivity {

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

		ProductDAO pdao = new ProductDAO(MenuInicial.this);
		List<Product> products = pdao.getAll();
		Log.i("Banco de dados", "---------------------");
		for (Product p : products) {
			Log.i("Banco de dados", "Produto: " + p.getName());
			if (p.getCategory() != null)
				Log.i("Banco de dados", "--" + p.getCategory().getId() + "/" + p.getCategory().getName());
			// Log.i("Banco de dados", "--" + p.getProvider().getId());
		}

		CategoryDAO cdao = new CategoryDAO(MenuInicial.this);
		List<Category> cs = cdao.getAll();
		Log.i("Banco de dados", "Qnt de categorias: " + cs.size());

		Log.i("Banco de dados", "++++++++++++++++");
		for (Category c : cs) {
			Log.i("Banco de dados", "Categoria: " + c.getName());
			for (Product pd : c.getProducts()) {
				Log.i("Banco de dados", "--: " + pd.getName());
			}
		}

		// ProviderDAO pvdao = new ProviderDAO(MenuInicial.this);
		// Log.i("Banco de dados", "Qnt de fornecedores: " +
		// pvdao.getAll().size());

		getValues();
	}

	@Override
	protected void onStart() {
		super.onStart();
		setCountsHeader();
	}

	private void setCountsHeader() {
		countCustomers = (Button)findViewById(R.id.count_customers);
		countProducts = (Button)findViewById(R.id.count_products);

		CustomerDAO cdao = new CustomerDAO(MenuInicial.this);
		countCustomers.setText(String.valueOf(cdao.getCount()));
		cdao.close();

		ProductDAO pdao = new ProductDAO(MenuInicial.this);
		countProducts.setText(String.valueOf(pdao.getCount()));
		pdao.close();
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
				startActivityForResult(i, 2);
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
