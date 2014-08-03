package com.mmidgard.vendas.news;

import java.text.NumberFormat;

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
import android.text.InputType;
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
		price.setInputType(InputType.TYPE_CLASS_NUMBER);
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

		private boolean isUpdating = false;
		// Pega a formatacao do sistema, se for brasil R$ se EUA US$
		private NumberFormat nf = NumberFormat.getCurrencyInstance();

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int after) {
			// Evita que o método seja executado varias vezes.
			// Se tirar ele entre em loop
			if (isUpdating) {
				isUpdating = false;
				return;
			}

			isUpdating = true;
			String str = s.toString();
			// Verifica se já existe a máscara no texto.
			boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str.indexOf(".") > -1 || str.indexOf(",") > -1));
			// Verificamos se existe máscara
			if (hasMask) {
				// Retiramos a máscara.
				str = str.replaceAll("[R$]", "").replaceAll("[,]", "").replaceAll("[.]", "");
			}

			try {
				// Transformamos o número que está escrito no EditText em
				// monetário.
				str = nf.format(Double.parseDouble(str) / 100);
				price.setText(str);
				price.setSelection(price.getText().length());
			} catch (NumberFormatException e) {
				s = "";
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// Não utilizamos
		}

		@Override
		public void afterTextChanged(Editable s) {
			// Não utilizamos
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
