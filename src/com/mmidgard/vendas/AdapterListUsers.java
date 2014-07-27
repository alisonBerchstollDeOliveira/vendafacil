package com.mmidgard.vendas;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListUsers extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Product> listUsuarios;

	public AdapterListUsers(Context context, List<Product> itens) {
		this.listUsuarios = itens;
		mInflater = LayoutInflater.from(context);
	}

	public void updateList(List<Product> listaNovosProdutos) {
		this.listUsuarios = listaNovosProdutos;
		notifyDataSetChanged();
	}

	public int getCount() {
		return listUsuarios.size();
	}

	public Product getItem(int position) {
		return listUsuarios.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, final View view, ViewGroup parent) {
		final Product user = listUsuarios.get(position);
		View v = mInflater.inflate(R.layout.item_cliente, null);

//		ImageView photo = (ImageView)v.findViewById(R.id.item_photo);
		TextView name = (TextView)v.findViewById(R.id.item_name);
		TextView date = (TextView)v.findViewById(R.id.item_date);
		TextView age = (TextView)v.findViewById(R.id.item_age);
		
		name.setText(user.getName());
		date.setText(user.getStock());
		age.setText(user.getValue());

		return v;
	}
}