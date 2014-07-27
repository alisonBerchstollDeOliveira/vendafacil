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

import com.mmidgard.vendas.AdapterListCategories;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.entity.Category;
import com.mmidgard.vendas.news.NewCategory;

public class ListCategories extends Activity {

	private AdapterListCategories adapterList;
	private ListView listview;
	private List<Category> listCategories;
	private Button newCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_categories);

		listview = (ListView)findViewById(R.id.list_categories);
		newCategory = (Button)findViewById(R.id.new_);
		newCategory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListCategories.this, NewCategory.class);
				startActivity(i);
				finish();
			}
		});

		updateList();
	}

	private void updateList() {
		listCategories = new ArrayList<Category>();
		Category c;
		c = new Category("Frutas", "descricao1");
		listCategories.add(c);
		c = new Category("Imoveis", "descricao2");
		listCategories.add(c);

		adapterList = new AdapterListCategories(ListCategories.this, listCategories);
		listview.setAdapter(adapterList);
	}

}
