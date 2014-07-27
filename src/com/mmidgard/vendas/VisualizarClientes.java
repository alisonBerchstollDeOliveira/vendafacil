package com.mmidgard.vendas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class VisualizarClientes extends Activity {

	private AdapterListUsers adapterList;
	private ListView listview;
	private List<Product> listUsers;
	private Button back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.visualizar_clientes);

		listview = (ListView)findViewById(R.id.list_users);
		back = (Button)findViewById(R.id.back);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		updateList();
	}

	private void updateList() {
		listUsers = new ArrayList<Product>();
		Product u;
		u = new Product("Melancia", "R$ 8,90", "22");
		listUsers.add(u);
		u = new Product("Televisão", "R$ 1081,90", "45");
		listUsers.add(u);
		u = new Product("Cadeira", "R$ 50,90", "76");
		listUsers.add(u);

		adapterList = new AdapterListUsers(VisualizarClientes.this, listUsers);
		listview.setAdapter(adapterList);
	}

}
