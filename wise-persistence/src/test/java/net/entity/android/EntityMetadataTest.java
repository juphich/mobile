package net.entity.android;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import net.entity.android.EntityReader;

import org.junit.Test;

import com.wise.note.Note;

public class EntityMetadataTest {

	@Test
	public void testEntityMetadata() {
		EntityReader reader = new EntityReader();
		
		EntityMetadata note = reader.read(Note.class);
		
		assertThat(note.getName(), is("note"));
		assertThat(note.getAttributes().size(), is(3));
	}
}
