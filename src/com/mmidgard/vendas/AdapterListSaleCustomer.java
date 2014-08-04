package com.mmidgard.vendas;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Customer;
import com.squareup.picasso.Picasso;

public class AdapterListSaleCustomer extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private List<Customer> listCustomers;
	private boolean userSelected = false;
	private RadioButton mCurrentlyCheckedRB;
	private Context context;

	public AdapterListSaleCustomer(Context context, List<Customer> itens) {
		this.listCustomers = itens;
		this.context = context;
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

	public View getView(final int position, View view, ViewGroup parent) {
		Customer customer = listCustomers.get(position);
		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = mInflater.inflate(R.layout.item_sale_customer, null);
		
		ImageView photo = (ImageView)view.findViewById(R.id.item_photo);
		RadioButton check = (RadioButton)view.findViewById(R.id.item_customer_check);
		TextView name = (TextView)view.findViewById(R.id.item_customer_name);
		TextView city = (TextView)view.findViewById(R.id.item_customer_city);
		TextView uf = (TextView)view.findViewById(R.id.item_customer_uf);

		String url = customer.getPathPhoto();
		if (!url.isEmpty() && new File(url).exists()) {
			 Picasso.with(context).load(new File(url)).into(photo);
		}
		
		if (position == getCount() - 1 && userSelected == false) {
			check.setChecked(true);
			mCurrentlyCheckedRB = check;
		} else {
			check.setChecked(false);
		}

		check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mCurrentlyCheckedRB != null) {
					if (mCurrentlyCheckedRB == null)
						mCurrentlyCheckedRB = (RadioButton)v;
					mCurrentlyCheckedRB.setChecked(true);
				}

				if (mCurrentlyCheckedRB == v)
					return;

				mCurrentlyCheckedRB.setChecked(false);
				((RadioButton)v).setChecked(true);
				mCurrentlyCheckedRB = (RadioButton)v;
			}
		});

		name.setText(customer.getName());
		city.setText(customer.getCity());
		uf.setText(customer.getUf());
		return view;
	}

}