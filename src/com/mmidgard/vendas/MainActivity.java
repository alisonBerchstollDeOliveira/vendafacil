package com.mmidgard.vendas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	private Button today;
	private Button rate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		getValues();
	}

	public void getValues() {
		today = (Button)findViewById(R.id.menu_clientes);
		rate = (Button)findViewById(R.id.menu_rate);

		today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, VisualizarClientes.class);
				startActivity(i);
			}
		});

		rate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				abrirGooglePlay("MMidgard");
			}
		});

	}

	public void abrirGooglePlay(String developerName) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=" + developerName)));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=" + developerName)));
		}
	}

}
