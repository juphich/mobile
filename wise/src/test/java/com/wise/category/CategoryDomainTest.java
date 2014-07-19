package com.wise.category;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryDomainTest {

	@Test
	public void testCategoryDomainTest() {
		Category partner = new Category("partner", "파트너", CategoryType.customer);
		Category friend = new Category("freind", "친구", CategoryType.customer);
		Category vip = new Category("vip", "우수고객", CategoryType.customer);
		
		assertNotNull(partner);
		assertNotNull(friend);
		assertNotNull(vip);
	}
}
