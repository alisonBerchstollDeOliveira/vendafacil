package com.mmidgard.vendas.news;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mmidgard.vendas.PhoneEditText;
import com.mmidgard.vendas.R;
import com.mmidgard.vendas.dao.CustomerDAO;
import com.mmidgard.vendas.entity.Customer;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class NewCustomer extends Activity {

	protected static final int RESULT_LOAD_IMAGE = 1;
	private ImageView photo;
	private EditText name;
	private PhoneEditText phone;
	private PhoneEditText cellPhone;
	private AutoCompleteTextView city;
	private EditText uf;
	private EditText street;
	private EditText number;
	private EditText complement;
	private EditText obs;
	private Button cancel;
	private Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_customer);

		buttonsClicks();
	}

	private void buttonsClicks() {
		photo = (ImageView)findViewById(R.id.customer_new_photo);
		name = (EditText)findViewById(R.id.customer_new_name);
		phone = (PhoneEditText)findViewById(R.id.customer_new_phone);
		cellPhone = (PhoneEditText)findViewById(R.id.customer_new_cellphone);
		city = (AutoCompleteTextView)findViewById(R.id.customer_new_city);
		uf = (EditText)findViewById(R.id.customer_new_uf);
		street = (EditText)findViewById(R.id.customer_new_street);
		number = (EditText)findViewById(R.id.customer_new_number);
		complement = (EditText)findViewById(R.id.customer_new_complement);
		obs = (EditText)findViewById(R.id.customer_new_obs);
		cancel = (Button)findViewById(R.id.cancel);
		save = (Button)findViewById(R.id.save);

		citysBD();

		photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);
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
					CustomerDAO cdao = new CustomerDAO(getApplicationContext());
					Customer customer = new Customer();
					customer.setName(name.getText().toString());
					customer.setPhone(phone.getText().toString());
					customer.setCellPhone(cellPhone.getText().toString());
					customer.setCity(city.getText().toString());
					customer.setUf(uf.getText().toString());
					customer.setNumber(number.getText().toString());
					customer.setComplement(complement.getText().toString());
					customer.setStreet(street.getText().toString());
					customer.setObs(obs.getText().toString());

					cdao.insert(customer);

					cdao.close();

					finish();
				}
			}
		});
	}

	private void citysBD() {
		CustomerDAO cdao = new CustomerDAO(getApplicationContext());
		List<String> citys = new ArrayList<String>();
		for (Customer c : cdao.getAll()) {
			if (!citys.contains(c.getCity()))
				citys.add(c.getCity());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, citys);
		city.setAdapter(adapter);
	}

	private boolean validate() {
		if (name.getText().toString().isEmpty()) {
			Crouton.makeText(NewCustomer.this, "Campo nome é obrigatório!", Style.ALERT).show();
			return false;
		} else if (city.getText().toString().isEmpty()) {
			Crouton.makeText(NewCustomer.this, "Campo cidade é obrigatório!", Style.ALERT).show();
			return false;
		} else if (uf.getText().toString().isEmpty()) {
			Crouton.makeText(NewCustomer.this, "Campo UF é obrigatório!", Style.ALERT).show();
			return false;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			Bitmap bMap = BitmapFactory.decodeFile(picturePath);

			photo.setImageBitmap(bMap);
		}
	}

}
