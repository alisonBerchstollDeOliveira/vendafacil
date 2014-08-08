package com.mmidgard.vendas;

import java.io.Serializable;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mmidgard.vendas.entity.Product;

public class AdapterListSaleProduct extends BaseAdapter implements Serializable {
	private static final long serialVersionUID = 5919068504936794402L;
	private LayoutInflater mInflater;
	private List<Product> listProducts;
	private Context context;
	private Dialog dialog;

	public AdapterListSaleProduct(Context context, List<Product> itens) {
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
		View v = mInflater.inflate(R.layout.item_sale_product, null);

		CheckBox check = (CheckBox)v.findViewById(R.id.item_product_check);
		final EditText qnt = (EditText)v.findViewById(R.id.item_product_qnt);
		TextView name = (TextView)v.findViewById(R.id.item_product_name);
		TextView value = (TextView)v.findViewById(R.id.item_product_value);
		TextView stock = (TextView)v.findViewById(R.id.item_product_stock);

		name.setText(product.getName());
		value.setText(String.valueOf(product.getCostPrice()));
		stock.setText(product.getStock());

		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					dialog = new Dialog(context);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.new_qnt);
					dialog.setCancelable(false);
					dialog.show();

					final EditText valueQnt = (EditText)dialog.findViewById(R.id.qnt_new_text);
					Button ok = (Button)dialog.findViewById(R.id.qnt_new_ok);
					ok.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							qnt.setText(valueQnt.getText().toString());
							product.setSelected(true);
							dialog.dismiss();
						}
					});

					Button btnRemove = (Button)dialog.findViewById(R.id.qnt_rmv);
					btnRemove.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							String valor = valueQnt.getText().toString();
							int s = Integer.parseInt(valor);
							s--;
							if (Integer.parseInt(product.getStock()) <= 0)
								valueQnt.setText(String.valueOf(s));
						}
					});

					Button btnAdd = (Button)dialog.findViewById(R.id.qnt_add);
					btnAdd.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							String valor = valueQnt.getText().toString();
							int s = Integer.parseInt(valor);
							s++;
							if (Integer.parseInt(product.getStock()) >= s)
								valueQnt.setText(String.valueOf(s));
							else
								Toast.makeText(context, valor + " é o estoque disponível deste produto.", Toast.LENGTH_LONG).show();
						}
					});
				} else {
					qnt.setText("");
					product.setSelected(false);
				}
			}
		});

		return v;
	}
}