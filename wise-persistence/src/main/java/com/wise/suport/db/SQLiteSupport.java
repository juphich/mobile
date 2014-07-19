package com.wise.suport.db;

public class SQLiteSupport {

	private SQLiteDatabaseManager manager;
	
	public SQLiteSupport(SQLiteDatabaseManager manager) {
		this.manager = manager;
		init();
	}
	
	protected final SQLiteDatabaseManager getSession() {
		return manager;
	}
	
	protected void init() { }
}
