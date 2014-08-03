package com.mmidgard.vendas;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.news.NewCustomer;
import com.squareup.picasso.Picasso;

public class AdapterListCustomers extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Customer> listCustomers;
	private Context context;

	public AdapterListCustomers(Context context, List<Customer> itens) {
		this.listCustomers = itens;
		this.context = context;
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
		final Customer customer = listCustomers.get(position);
		View v = mInflater.inflate(R.layout.item_customer, null);

		ImageView photo = (ImageView)v.findViewById(R.id.item_photo);
		TextView name = (TextView)v.findViewById(R.id.item_customer_name);
		TextView city = (TextView)v.findViewById(R.id.item_customer_city);
		ImageView edit = (ImageView)v.findViewById(R.id.customer_edit);

		String url = customer.getPathPhoto();
		if (!url.isEmpty() && new File(url).exists()) {
			 Picasso.with(context).load(new File(url)).into(photo);
		}

		name.setText(customer.getName());
		city.setText(customer.getCity());

		edit.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, NewCustomer.class);
				i.putExtra("customer", customer);
				context.startActivity(i);
			}
		});

		return v;
	}
}