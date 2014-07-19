package com.mmidgard.birthdayfacebook;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.mmidgard.birthdayfacebook.dao.LoginDAO;

public class LoginGeral extends Activity {

	private ImageButton loginFacebook;
	private static ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tela_login);

		loginFacebook = (ImageButton)findViewById(R.id.login_facebook);
		loginFacebook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(LoginGeral.this, null, null);
				dialog.setCancelable(false);
				dialog.setTitle("Aguarde");
				dialog.setMessage("Carregando...");
				dialog.show();

				// TODO adicionar à uma asyncTask
				// if (Wifi.testConnection()) {

				final Session s = new Session(LoginGeral.this);
				Session.setActiveSession(s);
				Session.OpenRequest request = new Session.OpenRequest(LoginGeral.this);
				request.setPermissions(Arrays.asList("public_profile", "email", "user_friends"));
				request.setCallback(new Session.StatusCallback() {
					@Override
					public void call(Session session, SessionState state, Exception exception) {
						if (session.isOpened()) {
							Request.newMeRequest(session, new Request.GraphUserCallback() {
								@Override
								public void onCompleted(GraphUser user, Response response) {
									if (user != null) {
										Login l = new Login();
										LoginDAO loginDao = new LoginDAO(LoginGeral.this);
										l.setNome(user.getName());
										l.setId(user.getId());
										loginDao.insert(l);
										getFriends();

										Intent i = new Intent(LoginGeral.this, MainActivity.class);
										startActivity(i);
										dialog.dismiss();
										finish();
									} else {
										Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
									}
								}
							}).executeAsync();
						}
					}
				});
				s.openForRead(request);
				// } else {
				// Toast.makeText(LoginGeral.this,
				// "Conecte-se à internet primeiramente.",
				// Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void getFriends() {
		Session activeSession = Session.getActiveSession();
		if (activeSession.getState().isOpened()) {
			Request friendRequest = Request.newMyFriendsRequest(activeSession, new GraphUserListCallback() {
				@Override
				public void onCompleted(List<GraphUser> users, Response response) {
					Log.i("INFO", response.toString());
				}
			});
			Bundle params = new Bundle();
			params.putString("fields", "id, name, picture");
			friendRequest.setParameters(params);
			friendRequest.executeAsync();
		}
		// String fqlQuery =
		// "select uid, name, pic_square, is_app_user from user where uid in (select uid2 from friend where uid1 = me())";
		// Bundle params = new Bundle();
		// params.putString("q", fqlQuery);
		//
		// Session session = Session.getActiveSession();
		// Request request = new Request(session, "/fql", params,
		// HttpMethod.GET, new Request.Callback() {
		// public void onCompleted(Response response) {
		// Log.i("A", "Got results: " + response.toString());
		// }
		// });
		// Request.executeBatchAsync(request);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

}
