package com.mmidgard.vendas;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Customer;

public class AdapterListCustomers extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Customer> listCustomers;

	public AdapterListCustomers(Context context, List<Customer> itens) {
		this.listCustomers = itens;
		mInflater = LayoutInflater.from(context);
	}

	public void updateList(List<Customer> listCustomers) {
		this.listCustomers = listCustomers;
		notifyDataSetChanged();
	}

	public int getCount() {
		return listCustomers.size();
	}

	public Customer getItem(int position) {
		return listCustomers.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, final View view, ViewGroup parent) {
		final Customer user = listCustomers.get(position);
		View v = mInflater.inflate(R.layout.item_customer, null);

		ImageView photo = (ImageView)v.findViewById(R.id.item_photo);
		TextView name = (TextView)v.findViewById(R.id.item_customer_name);
		TextView city = (TextView)v.findViewById(R.id.item_customer_city);
		TextView uf = (TextView)v.findViewById(R.id.item_customer_uf);

		String url = user.getPathPhoto();
		if (!url.isEmpty() && new File(url).exists()) {
			Bitmap bMap = BitmapFactory.decodeFile(url);
			photo.setImageBitmap(bMap);
		}

		name.setText(user.getName());
		city.setText(user.getCity());
		if (user.getUf().isEmpty())
			uf.setVisibility(View.GONE);
		else
			uf.setText(user.getUf());

		return v;
	}
}