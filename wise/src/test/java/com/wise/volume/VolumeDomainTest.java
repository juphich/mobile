package com.wise.volume;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wise.customer.Customer;
import com.wise.customer.CustomerBuilder;

public class VolumeDomainTest {

	private Customer master;
	private List<Customer> members;
	
	@Before
	public void init() {
		master = new CustomerBuilder().name("master").build();
		
		members = new ArrayList<>();
		for (int i=0; i<5; i++) {
			members.add(new CustomerBuilder().name("member" + i).build());
		}
	}
	
	@Test
	public void test_볼륨_그룹_도메인() {
		Volume volume = new Volume(master);
		
		volume.addMamber(members.get(0));
		volume.addMamber(members.get(1));
		volume.addMamber(members.get(2));
		volume.addMamber(members.get(3));
		
		assertThat(volume.getMaster(), is(master.getCustomerId()));
		assertThat(volume.getSize(), is(4));
	}
}
