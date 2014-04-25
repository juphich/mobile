package com.wise.note;

import java.util.Date;

public class Note {

	private String noteId;
	
	private Date writeTime;
	
	private String note;
	
	private Object referer;

	public Note(String noteId, String note) {
		this.noteId = noteId;
		this.note = note;
		this.writeTime = new Date();
	}
	
	public String getNoteId() {
		return noteId;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public String getNote() {
		return note;
	}

	public Object getReferer() {
		return referer;
	}

	public void setReferer(Object referer) {
		this.referer = referer;
	}
}
