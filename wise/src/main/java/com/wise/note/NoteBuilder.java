package com.wise.note;

import com.wise.core.SequenceGenerator;

public class NoteBuilder {

	private String noteId;
	
	private String content;
	
	private Object referer;
	
	public NoteBuilder() {
		this.noteId = SequenceGenerator.next("nt");
	}
	
	public NoteBuilder note(String note) {
		this.content = note;
		return this;
	}
	
	public NoteBuilder referer(Object referer) {
		this.referer = referer;
		return this;
	}
	
	public Note build() {
		Note note = new Note(noteId, content);
		note.setReferer(referer);
		
		return note;
	}
}
