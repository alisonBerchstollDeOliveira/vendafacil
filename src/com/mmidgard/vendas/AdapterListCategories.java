package com.mmidgard.vendas;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Category;

public class AdapterListCategories extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Category> listCategories;

	public AdapterListCategories(Context context, List<Category> itens) {
		this.listCategories = itens;
		mInflater = LayoutInflater.from(context);
	}

	public void updateList(List<Category> listaCategories) {
		this.listCategories = listaCategories;
		notifyDataSetChanged();
	}

	public int getCount() {
		return listCategories.size();
	}

	public Category getItem(int position) {
		return listCategories.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, final View view, ViewGroup parent) {
		final Category category = listCategories.get(position);
		View v = mInflater.inflate(R.layout.item_category, null);

		TextView name = (TextView)v.findViewById(R.id.item_name);
		TextView desc = (TextView)v.findViewById(R.id.item_desc);
		TextView size = (TextView)v.findViewById(R.id.item_size);

		name.setText(category.getName());
		desc.setText(category.getDescription());
		size.setText("10");

		return v;
	}
}