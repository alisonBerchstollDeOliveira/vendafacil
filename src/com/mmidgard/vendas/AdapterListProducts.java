package com.mmidgard.vendas;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Product;
import com.squareup.picasso.Picasso;

public class AdapterListProducts extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Product> listProducts;
	private Context context;

	public AdapterListProducts(Context context, List<Product> itens) {
		this.listProducts = itens;
		mInflater = LayoutInflater.from(context);
		this.context = context;
	}

	public void updateList(List<Product> listaNovosProdutos) {
		this.listProducts = listaNovosProdutos;
		notifyDataSetChanged();
	}

	public int getCount() {
		return listProducts.size();
	}

	public Product getItem(int position) {
		return listProducts.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, final View view, ViewGroup parent) {
		final Product product = listProducts.get(position);
		View v = mInflater.inflate(R.layout.item_product, null);

		ImageView photo = (ImageView)v.findViewById(R.id.item_photo);
		TextView name = (TextView)v.findViewById(R.id.item_product_name);
		TextView value = (TextView)v.findViewById(R.id.item_product_value);
		TextView stock = (TextView)v.findViewById(R.id.item_product_stock);
		TextView category = (TextView)v.findViewById(R.id.item_product_category);

		String url = product.getPathPhoto();
		if (!url.isEmpty() && new File(url).exists()) {
			Picasso.with(context).load(new File(url)).into(photo);
		}

		name.setText(product.getName());
		value.setText(String.valueOf(product.getCostPrice()));
		stock.setText(product.getStock());
		category.setText(product.getCategory().getName());

		return v;
	}
}