package com.wise.suport.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class SQLiteDatabaseManager extends SQLiteOpenHelper {

	private static final String path = Environment.getExternalStorageDirectory().getPath();
	private static final String dbName = "wise.db";
	
	private SQLiteDatabase db;
	
	public SQLiteDatabaseManager(Context context) {
		//super(context, path + "/" + dbName, null, 1); path로 db 생성이 안됨.
		super(context, dbName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void executeQuery(String query, Object... args) {
		getWritableDatabase();
		db.execSQL(query, args);
	}
	
	public Cursor executeSelect(String query, Object... args) {
		getReadableDatabase();
		
		String[] sarg = new String[args.length];
		for (int i=0; i<args.length; i++) {
			if (args[i] instanceof String) {
				sarg[i] = (String)args[i];
			} else {				
				sarg[i] = String.valueOf(args[i]);
			}
		}
		
		Cursor c = db.rawQuery(query, sarg);
		return c;
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		this.db = db;
	}
}
