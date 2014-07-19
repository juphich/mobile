package com.wise.volume;

import java.util.List;

import android.database.Cursor;

import com.wise.core.Query;
import com.wise.customer.Customer;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteVolumeRepository extends SQLiteSupport implements
		VolumeRepository {

	public SQLiteVolumeRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}
	
	@Override
	protected void init() {
		StringBuilder volume = new StringBuilder();
		volume.append("create table if not exists volume (")
			  .append("  master_id integer not null,")
			  .append("  member_id integer not null,")
			  .append("  primary key (master_id, member_id) )");
		
		getSession().executeQuery(volume.toString());
	}

	@Override
	public Volume find(String key) {
		getSession().executeSelect("select ", key);
		return null;
	}

	@Override
	public List<Volume> search(Query query) {
		//Cursor c = getSession().executeSelect("select m.customer_no, mfrom");
		
		return null;
	}

	@Override
	public void save(Volume domain) {
		for (Customer member : domain.getMembers()) {			
			getSession().executeQuery("insert or replace into volume (master_id, member_id) values (?, ?)", 
					domain.getMaster(), member.getCustomerId());
		}
	}

	@Override
	public void remove(Volume domain) {

	}

}