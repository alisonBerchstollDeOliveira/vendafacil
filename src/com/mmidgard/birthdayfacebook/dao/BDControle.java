package com.mmidgard.birthdayfacebook.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mmidgard.birthdayfacebook.Login;
import com.mmidgard.birthdayfacebook.User;

public class BDControle<E> extends OrmLiteSqliteOpenHelper {
	private static final String NOME_BD = "birthday.db";
	private static int VERSAO_BD = 1;

	public BDControle(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource src) {
		try {
			TableUtils.createTableIfNotExists(src, Login.class);
			TableUtils.createTableIfNotExists(src, User.class);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(src, Login.class, true);
			TableUtils.dropTable(src, User.class, true);

			onCreate(db, src);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
	}

}
