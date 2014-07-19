package com.wise.note;

import java.util.Date;
import java.util.List;

import com.wise.core.Query;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteNoteRepository extends SQLiteSupport implements NoteRepository {

	public SQLiteNoteRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}

	@Override
	protected void init() {
		System.out.println("crate note table.");
		
		StringBuilder note = new StringBuilder();
		note.append("CREATE TABLE IF NOT EXISTS note (")
			.append(" note_no INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,")
			.append(" note TEXT  NULL,")
			.append(" write_time DATE  NULL )");
		
		getSession().executeQuery(note.toString());
	}


	@Override
	public Note find(String key) {
		return null;
	}

	@Override
	public List<Note> search(Query query) {
		return null;
	}

	@Override
	public void save(Note domain) {
		getSession().executeQuery("insert into note (note, write_time) values (?, ?)", domain.getNote(), new Date());
	}

	@Override
	public void remove(Note domain) {

	}

}
