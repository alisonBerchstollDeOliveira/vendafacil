package com.mmidgard.vendas.news;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mmidgard.vendas.R;

public class NewProduct extends Activity {

	private Button cancel;
	private ImageButton addCategory;
	private ImageButton addProvider;
	private EditText price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_product);

		buttonsClicks();
	}

	private void buttonsClicks() {
		addCategory = (ImageButton)findViewById(R.id.add_category);
		addProvider = (ImageButton)findViewById(R.id.add_provider);
		price = (EditText)findViewById(R.id.product_new_price);
		cancel = (Button)findViewById(R.id.cancel);

		addCategory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createDialog();
				titulo.setText("Nova categoria");
				ok.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}
		});
		
		addProvider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				createDialog();
				titulo.setText("Novo fornecedor");
				ok.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		price.addTextChangedListener(tw);
	}
	
	public void createDialog()
	{
		dialog = new Dialog(NewProduct.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.new_category_provider);
		dialog.setCancelable(true);
		dialog.show();

		titulo = (TextView)dialog.findViewById(R.id.category_new_title);
		ok = (Button)dialog.findViewById(R.id.category_new_ok);
	}
	

	TextWatcher tw = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			if (!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
				String userInput = "" + s.toString().replaceAll("[^\\d]", "");
				StringBuilder cashAmountBuilder = new StringBuilder(userInput);

				while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
					cashAmountBuilder.deleteCharAt(0);
				}
				while (cashAmountBuilder.length() < 3) {
					cashAmountBuilder.insert(0, '0');
				}
				cashAmountBuilder.insert(cashAmountBuilder.length() - 2, ',');

				price.removeTextChangedListener(this);
				price.setText(cashAmountBuilder.toString());

				price.setTextKeepState("R$ " + cashAmountBuilder.toString());
				Selection.setSelection(price.getText(), cashAmountBuilder.toString().length() + 1);

				price.addTextChangedListener(this);
			}
		}
	};
	private Dialog dialog;
	private TextView titulo;
	private Button ok;
}
