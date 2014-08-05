package com.mmidgard.vendas.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.mmidgard.vendas.AdapterListSaleCustomer;
import com.mmidgard.vendas.AdapterListSaleProduct;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.dao.CustomerDAO;
import com.mmidgard.vendas.dao.ProductDAO;
import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.entity.Product;

public class NewSale extends Activity {

	private AdapterListSaleCustomer adapterListCustomer;
	private AdapterListSaleProduct adapterListProduct;
	private ListView listviewCustomers;
	private ListView listviewProducts;
	private List<Customer> listCustomers;
	private List<Product> listProducts;
	private Button nextStep;
	private Button menu1;
	private Button menu2;
	private Button menu3;
	private LinearLayout menuPagamento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_sale);

		listviewCustomers = (ListView)findViewById(R.id.list_customers);
		listviewCustomers.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listviewProducts = (ListView)findViewById(R.id.list_products);
		menuPagamento = (LinearLayout)findViewById(R.id.sale_menu_final);
		menu1 = (Button)findViewById(R.id.sale_menu_c);
		menu2 = (Button)findViewById(R.id.sale_menu_p);
		menu3 = (Button)findViewById(R.id.sale_menu_v);
		nextStep = (Button)findViewById(R.id.next_step);
		nextStep.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listviewCustomers.getVisibility() == View.VISIBLE) {
					listviewCustomers.setVisibility(View.GONE);
					listviewProducts.setVisibility(View.VISIBLE);
					menu1.setBackgroundColor(Color.parseColor("#00000000"));
					menu1.setTextColor(Color.parseColor("#ffffff"));
					menu2.setBackgroundColor(Color.parseColor("#ffffff"));
					menu2.setTextColor(Color.parseColor("#3E3E3C"));
				} else if (listviewProducts.getVisibility() == View.VISIBLE) {
					listviewProducts.setVisibility(View.GONE);
					menu2.setBackgroundColor(Color.parseColor("#00000000"));
					menu2.setTextColor(Color.parseColor("#ffffff"));
					menu3.setBackgroundColor(Color.parseColor("#ffffff"));
					menu3.setTextColor(Color.parseColor("#3E3E3C"));
					menuPagamento.setVisibility(View.VISIBLE);
					nextStep.setText("Finalizar venda");
				} else {
					Toast.makeText(NewSale.this, "Venda efetuada com sucesso!", Toast.LENGTH_LONG).show();
					finish();
				}

			}
		});

		updateListCustomers();
		updateListProducts();
	}

	private void updateListCustomers() {
		listCustomers = new ArrayList<Customer>();
		CustomerDAO cdao = new CustomerDAO(getApplicationContext());
		listCustomers = cdao.getAll();
		Collections.sort(listCustomers, Customer.getComparatorName());

		adapterListCustomer = new AdapterListSaleCustomer(NewSale.this, listCustomers);
		listviewCustomers.setAdapter(adapterListCustomer);

		cdao.close();
	}

	private void updateListProducts() {
		listProducts = new ArrayList<Product>();
		ProductDAO pdao = new ProductDAO(getApplicationContext());
		listProducts = pdao.getAll();
		Collections.sort(listProducts, Product.getComparatorName());

		adapterListProduct = new AdapterListSaleProduct(NewSale.this, listProducts);
		listviewProducts.setAdapter(adapterListProduct);

		pdao.close();
	}

}
