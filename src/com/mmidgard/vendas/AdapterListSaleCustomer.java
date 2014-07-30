package com.mmidgard.vendas;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mmidgard.vendas.entity.Customer;

public class AdapterListSaleCustomer extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private List<Customer> listCustomers;
	private boolean userSelected = false;
	private RadioButton mCurrentlyCheckedRB;
	private ViewHolder holder;
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

	private class ViewHolder {
		RadioButton check;
		TextView name;
		TextView city;
		TextView uf;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		holder = null;
		Customer customer = listCustomers.get(position);
		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = mInflater.inflate(R.layout.item_sale_customer, null);
			holder = new ViewHolder();
			holder.check = (RadioButton)view.findViewById(R.id.item_customer_check);
			holder.name = (TextView)view.findViewById(R.id.item_customer_name);
			holder.city = (TextView)view.findViewById(R.id.item_customer_city);
			holder.uf = (TextView)view.findViewById(R.id.item_customer_uf);
			view.setTag(holder);
		} else
			holder = (ViewHolder)view.getTag();

		if (position == getCount() - 1 && userSelected == false) {
			holder.check.setChecked(true);
			mCurrentlyCheckedRB = holder.check;
		} else {
			holder.check.setChecked(false);
		}

		holder.check.setOnClickListener(new OnClickListener() {

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

		holder.name.setText(customer.getName());
		holder.city.setText(customer.getCity());
		holder.uf.setText(customer.getUf());
		return view;
	}

}