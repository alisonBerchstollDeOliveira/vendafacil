package com.mmidgard.vendas.news;

import com.mmidgard.vendas.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class NewProduct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_product);
	}
}
