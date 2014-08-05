package com.mmidgard.vendas.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mmidgard.vendas.entity.Category;
import com.mmidgard.vendas.entity.Customer;
import com.mmidgard.vendas.entity.Product;
import com.mmidgard.vendas.entity.Provider;

public class BDControle<E> extends OrmLiteSqliteOpenHelper {
	private static final String NOME_BD = "vendas.db";
	private static int VERSAO_BD = 1;

	public BDControle(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource src) {
		try {
			TableUtils.createTableIfNotExists(src, Customer.class);
			TableUtils.createTableIfNotExists(src, Product.class);
			TableUtils.createTableIfNotExists(src, Category.class);
			TableUtils.createTableIfNotExists(src, Provider.class);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(src, Customer.class, true);
			TableUtils.dropTable(src, Product.class, true);
			TableUtils.dropTable(src, Category.class, true);
			TableUtils.dropTable(src, Provider.class, true);

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
