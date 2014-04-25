package com.wise.note;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class NoteDomainTest {

	@Test
	public void test_메모_작성() {
		Note note = new NoteBuilder().note("테스트 메모").build();
		
		assertNotNull(note.getNoteId());
		assertNotNull(note.getWriteTime());
		assertThat(note.getNote(), is("테스트 메모"));
	}
}
