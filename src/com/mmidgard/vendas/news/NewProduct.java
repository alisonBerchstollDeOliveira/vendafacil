package com.mmidgard.vendas.news;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmidgard.vendas.R;
import com.mmidgard.vendas.dao.ProductDAO;
import com.mmidgard.vendas.entity.Product;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class NewProduct extends Activity {

	protected static final int RESULT_LOAD_IMAGE = 1;
	private String pathPhoto = "";
	private ImageButton addCategory;
	private ImageButton addProvider;
	private TextView categoryText;
	private Dialog dialog;
	private TextView titulo;
	private Button ok;
	private Product product;
	private Button cancel;
	private Button save;
	private boolean edit = false;

	private ImageView photo;
	private EditText code;
	private EditText name;
	private EditText description;
	private EditText price;
	private EditText stock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_product);

		product = new Product();

		buttonsClicks();
	}

	private void buttonsClicks() {
		photo = (ImageView)findViewById(R.id.product_new_photo);
		code = (EditText)findViewById(R.id.product_new_code);
		name = (EditText)findViewById(R.id.product_new_name);
		description = (EditText)findViewById(R.id.product_new_description);
		price = (EditText)findViewById(R.id.product_new_price);
		stock = (EditText)findViewById(R.id.product_new_stock);

		addCategory = (ImageButton)findViewById(R.id.add_category);
		addProvider = (ImageButton)findViewById(R.id.add_provider);
		cancel = (Button)findViewById(R.id.cancel);
		save = (Button)findViewById(R.id.save);

		photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});

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

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validate()) {
					ProductDAO cdao = new ProductDAO(getApplicationContext());
					product.setCode(code.getText().toString());
					product.setPathPhoto(pathPhoto);
					product.setName(name.getText().toString());
					product.setDescription(description.getText().toString());
					product.setCostPrice(price.getText().toString());
					product.setStock(stock.getText().toString());

					if (edit)
						cdao.update(product);
					else
						cdao.insert(product);

					cdao.close();

					setResult(2);
					finish();
				}
			}
		});

		price.addTextChangedListener(tw);
	}

	private boolean validate() {
		if (code.getText().toString().isEmpty()) {
			Crouton.makeText(NewProduct.this, "Campo código é obrigatório!", Style.ALERT).show();
			return false;
		} else if (name.getText().toString().isEmpty()) {
			Crouton.makeText(NewProduct.this, "Campo nome é obrigatório!", Style.ALERT).show();
			return false;
		} else if (price.getText().toString().isEmpty()) {
			Crouton.makeText(NewProduct.this, "Campo preço é obrigatório!", Style.ALERT).show();
			return false;
		} else if (stock.getText().toString().isEmpty()) {
			Crouton.makeText(NewProduct.this, "Campo estoque é obrigatório!", Style.ALERT).show();
			return false;
		}
		return true;
	}

	public void createDialog() {
		dialog = new Dialog(NewProduct.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.new_category_provider);
		dialog.setCancelable(true);
		dialog.show();

		titulo = (TextView)dialog.findViewById(R.id.category_new_title);
		categoryText = (TextView)dialog.findViewById(R.id.category_new_text);
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
			if (!s.toString().matches("^\\$(\\d{1,3}(\\.\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
				String userInput = "" + s.toString().replaceAll("[^\\d]", "");
				StringBuilder cashAmountBuilder = new StringBuilder(userInput);

				while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
					cashAmountBuilder.deleteCharAt(0);
				}
				while (cashAmountBuilder.length() < 3) {
					cashAmountBuilder.insert(0, '0');
				}
				cashAmountBuilder.insert(cashAmountBuilder.length() - 2, '.');

				price.removeTextChangedListener(this);
				price.setText(cashAmountBuilder.toString());

				price.setTextKeepState("R$ " + cashAmountBuilder.toString());
				Selection.setSelection(price.getText(), cashAmountBuilder.toString().length() + 1);

				price.addTextChangedListener(this);
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			pathPhoto = cursor.getString(columnIndex);
			cursor.close();

			Bitmap bMap = BitmapFactory.decodeFile(pathPhoto);

			photo.setImageBitmap(bMap);
		}
	}

}
