package com.mmidgard.vendas;

import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public final class Wifi extends View {

	public Wifi(Context context) {
		super(context);
	}

	private static WifiManager wifi;
	protected boolean connecting;
	private static Wifi instance;

	public static Wifi getInstance(Context context) {
		if (instance == null && context != null)
			instance = new Wifi(context);
		return instance;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		wifi = (WifiManager)getContext().getSystemService(Context.WIFI_SERVICE);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		Bundle bundle = (Bundle)state;
		Parcelable superState = bundle.getParcelable("superState");
		super.onRestoreInstanceState(superState);
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();

		Bundle state = new Bundle();
		state.putParcelable("superState", superState);
		return state;
	}

	public static boolean testConnection() {

		if (wifi != null && !wifi.isWifiEnabled()) {
			return false;
		}
		URL url;
		try {
			url = new URL("http://186.192.82.163"); // www.globo.com
			HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(2000);
			urlc.connect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}