package com.wise.note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.note.Note;
import com.wise.note.NoteRepository;

public class InMemoryNoteRepository implements NoteRepository {

	private Map<Object, List<Note>> notes = new HashMap<Object, List<Note>>();
	
	@Override
	public Note find(String key) {
		return null;
	}

	@Override
	public List<Note> search(Query query) {
		Object ref = query.key("referer");
		
		return notes.get(ref);
	}

	@Override
	public void save(Note note) {
		List<Note> list = notes.get(note.getReferer());
		
		if (list == null) {
			list = new ArrayList<>();
		}
		
		list.add(note);
		notes.put(note.getReferer(), list);
	}

	@Override
	public void remove(Note note) {
		notes.remove(note);
	}
}
