package com.mmidgard.birthdayfacebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmidgard.birthdayfacebook.dao.LoginDAO;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

public class MainActivity extends FragmentActivity {

	private Button today;
	private AdapterListUsers adapterList;
	private ListView listview;
	private List<User> listUsers;
	private LinearLayout container;
	private Button back;
	private Button thisMonth;
	private Button next5;
	private Button all;
	private Button rate;
	private CaldroidFragment dialogCaldroidFragment;
	private Button selectDay;
	private Bundle teste;
	private TextView userName;
	private TextView userFriends;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		if (savedInstanceState != null) {
			dialogCaldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
		}

		teste = savedInstanceState;

		getValues();
		getLogin();
	}

	private void getLogin() {
		LoginDAO loginDao = new LoginDAO(MainActivity.this);
		Login login = loginDao.getAll().get(0);
		userName.setText(login.getNome());
	}

	public void getValues() {
		listview = (ListView)findViewById(R.id.list_users);
		back = (Button)findViewById(R.id.back);
		container = (LinearLayout)findViewById(R.id.container);
		today = (Button)findViewById(R.id.menu_today);
		thisMonth = (Button)findViewById(R.id.menu_this_month);
		next5 = (Button)findViewById(R.id.menu_next_5);
		all = (Button)findViewById(R.id.menu_all);
		rate = (Button)findViewById(R.id.menu_rate);
		selectDay = (Button)findViewById(R.id.menu_select);
		userName = (TextView) findViewById(R.id.user_name);
		userFriends = (TextView) findViewById(R.id.user_friends);

		today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideMenu();
				updateList();
			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewMenu();
			}
		});

		thisMonth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideMenu();
				updateList();
			}
		});

		next5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideMenu();
				updateList();
			}
		});

		all.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideMenu();
				updateList();
			}
		});

		rate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				abrirGooglePlay("MMidgard");
			}
		});

		selectDay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				criarCalendario();
			}
		});

	}

	private void updateList() {
		listUsers = new ArrayList<User>();
		User u;
		u = new User("Lucas Rafagnin", "08-11-2014", "22");
		listUsers.add(u);
		u = new User("Teste Sobrenome", "05-09-2013", "45");
		listUsers.add(u);
		u = new User("Lucas Rafagnin", "02-04-2012", "76");
		listUsers.add(u);

		adapterList = new AdapterListUsers(MainActivity.this, listUsers);
		listview.setAdapter(adapterList);
	}

	private void hideMenu() {
		container.setVisibility(View.GONE);
		listview.setVisibility(View.VISIBLE);
		back.setVisibility(View.VISIBLE);
	}

	private void viewMenu() {
		listview.setVisibility(View.GONE);
		back.setVisibility(View.GONE);
		container.setVisibility(View.VISIBLE);
	}

	public void abrirGooglePlay(String developerName) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=" + developerName)));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=" + developerName)));
		}
	}

	private void criarCalendario() {
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				hideMenu();
				updateList();
				dialogCaldroidFragment.dismiss();
			}

			@Override
			public void onChangeMonth(int month, int year) {
			}

			@Override
			public void onLongClickDate(Date date, View view) {
			}

			@Override
			public void onCaldroidViewCreated() {
			}

		};

		dialogCaldroidFragment = new CaldroidFragment();
		dialogCaldroidFragment.setCaldroidListener(listener);

		final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
		if (teste != null) {
			dialogCaldroidFragment.restoreDialogStatesFromKey(getSupportFragmentManager(), teste, "DIALOG_CALDROID_SAVED_STATE", dialogTag);
			Bundle args = dialogCaldroidFragment.getArguments();
			if (args == null) {
				args = new Bundle();
				dialogCaldroidFragment.setArguments(args);
			}
			args.putString(CaldroidFragment.DIALOG_TITLE, "Select a date");
		} else {
			Bundle bundle = new Bundle();
			bundle.putString(CaldroidFragment.DIALOG_TITLE, "Select a date");
			dialogCaldroidFragment.setArguments(bundle);
		}

		dialogCaldroidFragment.show(getSupportFragmentManager(), dialogTag);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState, "DIALOG_CALDROID_SAVED_STATE");
		}
	}

}
